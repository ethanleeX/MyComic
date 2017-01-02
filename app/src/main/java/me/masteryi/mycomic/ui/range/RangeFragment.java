package me.masteryi.mycomic.ui.range;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.masteryi.mycomic.databinding.FragmentRangeBinding;
import me.masteryi.mycomic.ui.BaseComicList.BaseComicListFragment;

public class RangeFragment extends BaseComicListFragment<RangePresenter> {
    private static final String ORDER = "4";
    private FragmentRangeBinding mBinding;

    public RangeFragment () {
    }

    @Override
    protected View inflateView (LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
        mBinding = FragmentRangeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView () {
        mRecyclerView = mBinding.rangeRecyclerView;
        mSwipeRefreshLayout = mBinding.swipeRefreshLayout;
        super.initView();
    }

    @Override
    protected void initPresenter () {
        mPresenter = new RangePresenter(this);
    }

    @Override
    protected String getOrder () {
        return ORDER;
    }

    @Override
    public void onGetDataByPageFinish () {
        // TODO: 2017/1/2
    }
}
