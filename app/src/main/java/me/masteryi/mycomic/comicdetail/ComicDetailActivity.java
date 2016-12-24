package me.masteryi.mycomic.comicdetail;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseActivity;
import me.masteryi.mycomic.databinding.ActivityComicDetailBinding;
import me.masteryi.mycomic.model.beans.ComicChapter;
import me.masteryi.mycomic.model.beans.ComicDetail;

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
    private String mComicId;
    private String mChapterId;
    private String mTitle;

    @Override
    protected void getExtraData () {
        mComicId = getIntent().getStringExtra(COMIC_ID);
        mChapterId = getIntent().getStringExtra(CHAPTER_ID);
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
        mBinding.comicDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initPresenter () {
        mPresenter = new ComicDetailPresenter(this);
    }

    @Override
    protected void initData () {
        //mPresenter.getPageCount(mComicId, mChapterId);
        mPresenter.getComicDetail(mComicId, mChapterId);
    }

    @Override
    public void getComicDetailSuccess (ComicDetail comicDetail) {
        mComicDetailAdapter.updateData(comicDetail);
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
        mComicDetailAdapter.clearData();
        mChapterId = isNext ? nextChapter.getNextId() : nextChapter.getPreviousId();
        //获取下一页数据
        //mPresenter.getPageCount(mComicId, mChapterId);
        mPresenter.getComicDetail(mComicId, mChapterId);
    }

    @Override
    public void getNextChapterFailure (Throwable t) {
        showErrorMsg(t);
    }

    @Override
    public void getNextChapterFinish () {
        //TODO
    }
}
