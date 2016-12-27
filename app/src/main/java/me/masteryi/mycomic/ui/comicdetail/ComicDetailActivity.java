package me.masteryi.mycomic.ui.comicdetail;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.orhanobut.logger.Logger;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseActivity;
import me.masteryi.mycomic.beans.ComicChapter;
import me.masteryi.mycomic.beans.ComicDetail;
import me.masteryi.mycomic.beans.ComicPageDetail;
import me.masteryi.mycomic.databinding.ActivityComicDetailBinding;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 *
 * 漫画详情页
 */
public class ComicDetailActivity extends BaseActivity<ComicDetailPresenter>
    implements ComicDetailContract.IView {
    public static final String COMIC_ID = "comic_id";
    public static final String CHAPTER_ID = "chapter_id";
    public static final String TITLE = "title";
    private ActivityComicDetailBinding mBinding;
    private ComicDetailAdapter mComicDetailAdapter;
    private LinearLayoutManager mLayoutManager;
    private String mComicId;
    private String mFirstChapterId;
    private String mLastChapterId;
    private int mCurrentPage = -1;
    private String mTitle;

    @Override
    protected void getExtraData () {
        mComicId = getIntent().getStringExtra(COMIC_ID);
        mFirstChapterId = getIntent().getStringExtra(CHAPTER_ID);
        mLastChapterId = getIntent().getStringExtra(CHAPTER_ID);
        mTitle = getIntent().getStringExtra(TITLE);
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
    }

    @Override
    public void getComicDetailSuccess (ComicDetail comicDetail, boolean isLoadNext) {
        mComicDetailAdapter.updateData(comicDetail, isLoadNext);
    }

    @Override
    public void getComicDetailFinish () {
        //TODO
    }

    @Override
    public void getComicDetailFailure (Throwable t) {
        showErrorMsg(t);
    }

    @Override
    public void getNextChapterSuccess (ComicChapter nextChapter, boolean isNext) {
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
}
