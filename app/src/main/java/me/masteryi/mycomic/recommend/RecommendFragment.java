package me.masteryi.mycomic.recommend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseFragment;
import me.masteryi.mycomic.beans.RecommendComic;
import me.masteryi.mycomic.databinding.FragmentRecommendBinding;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */
public class RecommendFragment extends BaseFragment<RecommendPresenter>
    implements RecommendContract.View {
    private FragmentRecommendBinding mBinding;
    RecyclerView mRecommendRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecommendAdapter mRecommendAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public RecommendFragment () {
        // Required empty public constructor
    }

    @Override
    protected int getContentId () {
        return R.layout.fragment_recommend;
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
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecommendRecyclerView.setAdapter(mRecommendAdapter);
        mRecommendRecyclerView.setLayoutManager(mLayoutManager);

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
                mPresenter.loadData();
            }
        });
    }

    @Override
    protected void initPresenter () {
        mPresenter = new RecommendPresenter(this);
    }

    @Override
    public void loadDataFail (Throwable t) {
        t.printStackTrace();
        showErrorMsg(t.getMessage());
    }

    @Override
    public void loadDataSuccess (List<RecommendComic> recommendComics) {
        mRecommendAdapter.refreshData(recommendComics);
    }

    @Override
    public void loadDataFinish () {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
