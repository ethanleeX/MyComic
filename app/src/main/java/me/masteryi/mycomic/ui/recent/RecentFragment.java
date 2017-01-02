package me.masteryi.mycomic.ui.recent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.masteryi.mycomic.databinding.FragmentLatestUpdateBinding;
import me.masteryi.mycomic.ui.BaseComicList.BaseComicListFragment;

/**
 * 最近更新
 *
 * @author master.yi
 * @date 2017/1/1
 * @blog masteryi.me
 */
public class RecentFragment extends BaseComicListFragment<RecentPresenter> {
    private static final String ORDER = "1";
    private FragmentLatestUpdateBinding mBinding;

    public RecentFragment () {
    }

    @Override
    protected View inflateView (LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
        mBinding = FragmentLatestUpdateBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView () {
        mRecyclerView = mBinding.latestUpdateRecyclerView;
        mSwipeRefreshLayout = mBinding.swipeRefreshLayout;
        super.initView();
    }

    @Override
    protected String getOrder () {
        return ORDER;
    }

    @Override
    protected void initPresenter () {
        mPresenter = new RecentPresenter(this);
    }

    @Override
    public void onGetDataByPageFinish () {
        // TODO: 2017/1/2
    }
}
