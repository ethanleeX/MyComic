package me.masteryi.mycomic.ui.recommend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import me.masteryi.mycomic.base.BaseFragment;
import me.masteryi.mycomic.beans.RecommendComic;
import me.masteryi.mycomic.databinding.FragmentRecommendBinding;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */
public class RecommendFragment extends BaseFragment<RecommendPresenter>
    implements RecommendContract.IView {
    public static final int COVER_COUNT_PER_LIST = 6;
    private FragmentRecommendBinding mBinding;
    RecyclerView mRecommendRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecommendAdapter mRecommendAdapter;
    private GridLayoutManager mLayoutManager;

    public RecommendFragment () {
    }

    @Override
    protected View inflateView (LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
        mBinding = FragmentRecommendBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView () {
        mRecommendRecyclerView = mBinding.recommendRecyclerView;
        mSwipeRefreshLayout = mBinding.swipeRefreshLayout;

        mRecommendAdapter = new RecommendAdapter(getContext());
        mLayoutManager = new GridLayoutManager(getContext(), RecommendAdapter.COVER_COUNT_ONE_LINE);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize (int position) {
                return mRecommendAdapter.getItemViewType(position) == RecommendAdapter.TYPE_TITLE
                    ? RecommendAdapter.COVER_COUNT_ONE_LINE : 1;
            }
        });
        mRecommendRecyclerView.setAdapter(mRecommendAdapter);
        mRecommendRecyclerView.setLayoutManager(mLayoutManager);
        mRecommendRecyclerView.addItemDecoration(new RecommendItemDecoration(getContext()));
        //刚进入时刷新
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run () {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh () {
                mPresenter.getRecommendList();
            }
        });
    }

    @Override
    protected void initPresenter () {
        mPresenter = new RecommendPresenter(this);
    }

    @Override
    protected void initData () {
        mPresenter.getRecommendList();
    }

    @Override
    public void loadDataFailure (Throwable t) {
        showErrorMsg(t);
    }

    @Override
    public void loadDataSuccess (List<RecommendComic> recommendComics) {
        mRecommendAdapter.initData(recommendComics);
    }

    @Override
    public void loadDataFinish () {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
