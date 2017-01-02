package me.masteryi.mycomic.ui.comicintroduction;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseToolbarActivity;
import me.masteryi.mycomic.beans.ComicIntroductionDetail;
import me.masteryi.mycomic.databinding.ActivityComicChapterBinding;
import me.masteryi.mycomic.utils.BlurPostprocessor;

public class ComicIntroductionActivity extends BaseToolbarActivity<ComicIntroductionPresenter>
    implements ComicIntroductionContract.IView {
    public static final String URL = "url";
    public static final String TITLE = "title";
    public static final String COVER = "cover";

    private ActivityComicChapterBinding mBinding;
    private IntroductionAdapter mIntroductionAdapter;
    private String mUrl;
    private String mTitle;
    private String mCover;
    private View mFailView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void getExtraData () {
        mUrl = getIntent().getStringExtra(URL);
        mTitle = getIntent().getStringExtra(TITLE);
        mCover = getIntent().getStringExtra(COVER);
    }

    @Override
    protected void initView () {
        super.initView();

        mIntroductionAdapter = new IntroductionAdapter(this);
        mBinding.chapterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.chapterRecyclerView.setAdapter(mIntroductionAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
            DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        mBinding.chapterRecyclerView.addItemDecoration(dividerItemDecoration);

        //背景图模糊
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(mCover))
                                                       .setPostprocessor(new BlurPostprocessor())
                                                       .build();

        PipelineDraweeController draweeController
            = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                                               .setImageRequest(imageRequest)
                                               .setOldController(
                                                   mBinding.titleImage.getController())
                                               .build();

        mBinding.titleImage.setController(draweeController);

        mBinding.collapsingToolbar.setTitle(mTitle);
        mBinding.appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged (AppBarLayout appBarLayout, int verticalOffset) {
                //完全展开状态
                if(verticalOffset == 0) {
                    mBinding.titleImageSmall.animate().scaleX(1).scaleY(1).setDuration(200).start();
                } else {
                    mBinding.titleImageSmall.animate().scaleX(0).scaleY(0).setDuration(200).start();
                }
            }
        });
    }

    @Override
    protected void initPresenter () {
        mPresenter = new ComicIntroductionPresenter(this);
    }

    @Override
    protected void initData () {
        mBinding.loading.setVisibility(View.VISIBLE);
        mPresenter.loadData(mUrl);
    }

    @Override
    protected void initContentView () {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_comic_chapter);
        mBinding.setCover(mCover);
    }

    @Override
    protected void setToolbar () {
        mToolbar = mBinding.toolbar;
    }

    @Override
    public void loadDataSuccess (ComicIntroductionDetail comicIntroductionDetail) {
        if(mFailView != null) {
            mFailView.setVisibility(View.GONE);
        }
        comicIntroductionDetail.setTitle(mTitle);
        mBinding.setComicIntroductionDetail(comicIntroductionDetail);
        mIntroductionAdapter.update(comicIntroductionDetail.getIntroduction(),
            comicIntroductionDetail.getChapters());
    }

    @Override
    public void loadDataFinish () {
        mBinding.loading.setVisibility(View.GONE);
    }

    @Override
    public void loadDataFailure (Throwable t) {
        showErrorMsg(t);
        showFailView();
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
}
