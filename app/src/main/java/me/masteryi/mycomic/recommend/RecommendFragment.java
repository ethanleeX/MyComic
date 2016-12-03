package me.masteryi.mycomic.recommend;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import java.util.List;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseFragment;
import me.masteryi.mycomic.beans.RecommendComic;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */
public class RecommendFragment extends BaseFragment<RecommendPresenter>
    implements RecommendContract.View<RecommendPresenter> {
    @BindView(R.id.recommend_recycler_view)
    RecyclerView mRecommendRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
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
    protected void initView () {
        mRecommendAdapter = new RecommendAdapter(getContext());
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecommendRecyclerView.setAdapter(mRecommendAdapter);
        mRecommendRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));//刚进入时刷新
    }

    @Override
    public void loadDataFail (Throwable t) {
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
