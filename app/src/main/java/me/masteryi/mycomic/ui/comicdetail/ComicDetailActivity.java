package me.masteryi.mycomic.ui.comicdetail;

import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseActivity;
import me.masteryi.mycomic.beans.NextChapterInfo;
import me.masteryi.mycomic.beans.ComicContent;
import me.masteryi.mycomic.beans.ComicPageDetail;
import me.masteryi.mycomic.broadcast.NetworkStateReceiver;
import me.masteryi.mycomic.constant.IntentExtraKey;
import me.masteryi.mycomic.constant.MyEventAction;
import me.masteryi.mycomic.databinding.ActivityComicDetailBinding;
import me.masteryi.mycomic.utils.ActivityUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 漫画详情页
 *
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */
public class ComicDetailActivity extends BaseActivity<ComicDetailPresenter>
    implements ComicDetailContract.IView {
    private ActivityComicDetailBinding mBinding;
    private ComicDetailAdapter mComicDetailAdapter;
    private LinearLayoutManager mLayoutManager;
    private View mFailView;
    private String mComicId;
    private String mFirstChapterId;
    private String mLastChapterId;
    private int mCurrentPage = -1;
    private String mTitle;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private SimpleDateFormat mSdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private NetworkStateReceiver mNetworkStateReceiver;

    @Override
    protected void getExtraData () {
        mComicId = getIntent().getStringExtra(IntentExtraKey.COMIC_ID);
        mFirstChapterId = getIntent().getStringExtra(IntentExtraKey.CHAPTER_ID);
        mLastChapterId = getIntent().getStringExtra(IntentExtraKey.CHAPTER_ID);
        mTitle = getIntent().getStringExtra(IntentExtraKey.TITLE);
    }

    @Override
    protected void initContentView () {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_comic_detail);
    }

    @Override
    protected void initView () {
        mComicDetailAdapter = new ComicDetailAdapter(this);
        mBinding.comicDetailRecyclerView.setAdapter(mComicDetailAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mBinding.comicDetailRecyclerView.setLayoutManager(mLayoutManager);

        mBinding.comicDetailRecyclerView.scrollTo(0, 100);

        mBinding.comicDetailRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled (RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //向上拉 并且当前处于第一页 加载上一章
                if(dy < 0 && mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    mPresenter.getNextChapter(mComicId, mFirstChapterId, false);
                    showMessage(R.string.load_previous_chapter);
                }
                //向下拉 并且处于最后一页 加载下一章
                if(dy > 0 &&
                   mLayoutManager.findLastCompletelyVisibleItemPosition() ==
                   mComicDetailAdapter.getItemCount() - 1) {
                    mPresenter.getNextChapter(mComicId, mLastChapterId, true);
                    showMessage(R.string.load_next_chapter);
                }
                int firstPosition = mLayoutManager.findFirstVisibleItemPosition();
                ComicPageDetail comicPageDetail = mComicDetailAdapter.getItem(firstPosition);
                if(mCurrentPage < 0 || comicPageDetail.getPage() != mCurrentPage) {
                    mCurrentPage = comicPageDetail.getPage();
                    mBinding.page.setText(getString(R.string.page_index, mCurrentPage + 1,
                        comicPageDetail.getTotalPageCount()));
                    mBinding.title.setText(comicPageDetail.getChapterName());
                }
            }
        });
    }

    @Override
    protected void initPresenter () {
        mPresenter = new ComicDetailPresenter(this);
    }

    @Override
    protected void initData () {
        mPresenter.getComicDetail(mComicId, mFirstChapterId, true);
        mBinding.loading.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume () {
        super.onResume();
        EventBus.getDefault().register(this);
        registerNetworkStateReceiver();
    }

    @Override
    protected void onPause () {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        mCompositeDisposable.clear();
        unregisterReceiver(mNetworkStateReceiver);
    }

    @Override
    public void getComicDetailSuccess (ComicContent comicContent, boolean isLoadNext) {
        mComicDetailAdapter.updateData(comicContent, isLoadNext);
        if(mFailView != null) {
            mFailView.setVisibility(View.GONE);
        }

        initInfo();
    }

    @Override
    public void getComicDetailFinish () {
        mBinding.loading.setVisibility(View.GONE);
    }

    @Override
    public void getComicDetailFailure (Throwable t) {
        showErrorMsg(t);
        showFailView();
    }

    @Override
    public void getNextChapterSuccess (NextChapterInfo nextChapter, boolean isNext) {
        //加载下一章
        if(isNext) {
            if(nextChapter.getNextId().equals("0")) {
                showMessage(R.string.no_more_chapter);
                return;
            }
            mLastChapterId = nextChapter.getNextId();
            mTitle = nextChapter.getNextTitle();
            mPresenter.getComicDetail(mComicId, mLastChapterId, true);
        } else {
            if(nextChapter.getPreviousId().equals("0")) {
                showMessage(R.string.no_more_chapter);
                return;
            }
            mFirstChapterId = nextChapter.getPreviousId();
            mTitle = nextChapter.getPreviousTitle();
            mPresenter.getComicDetail(mComicId, mFirstChapterId, false);
        }
    }

    @Override
    public void getNextChapterFailure (Throwable t) {
        showErrorMsg(t);
    }

    @Override
    public void getNextChapterFinish () {
        showMessage(R.string.load_chapter_finish);
    }

    private void showFailView () {
        if(mFailView == null) {
            mFailView = mBinding.failViewStub.getViewStub().inflate();
        }
        mFailView.setVisibility(View.VISIBLE);
        View retryView = mFailView.findViewById(R.id.retry);
        retryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                mFailView.setVisibility(View.GONE);
                initData();
            }
        });
    }

    /**
     * 设置定时更新时间 检查电量和网络状态变化
     */
    private void initInfo () {
        //一秒钟更新一下时间
        mCompositeDisposable.add(Flowable.interval(0, 1, TimeUnit.SECONDS)
                                         .observeOn(AndroidSchedulers.mainThread())
                                         .subscribe(new Consumer<Long>() {
                                             @Override
                                             public void accept (Long aLong) throws Exception {
                                                 String time = mSdf.format(new Date());
                                                 mBinding.time.setText(time);
                                             }
                                         }));
        //设置网络状态信息
        setNetWorkStateInfo();
    }

    /**
     * 注册网络状态动态广播
     */
    private void registerNetworkStateReceiver () {
        if(mNetworkStateReceiver == null) {
            mNetworkStateReceiver = new NetworkStateReceiver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkStateReceiver, intentFilter);
    }

    /**
     * 网络状态改变
     */
    @Subscribe
    public void onEvent (Object action) {
        if(action.equals(MyEventAction.NETWORK_STATE_CHANGED)) {
            setNetWorkStateInfo();
        }
    }

    /**
     * 设置网络状态信息
     */
    private void setNetWorkStateInfo () {
        switch (ActivityUtil.getNetWorkState(this)) {
            case ActivityUtil.NETWORK_OFFLINE:
                mBinding.networkState.setText(R.string.network_type_offline);
                break;
            case ActivityUtil.NETWORK_MOBILE:
                mBinding.networkState.setText(R.string.network_type_mobile);
                break;
            case ActivityUtil.NETWORK_WIFI:
                mBinding.networkState.setText(R.string.network_type_wifi);
                break;
            default:
                mBinding.networkState.setText(R.string.network_type_unknown);
        }
    }
}
