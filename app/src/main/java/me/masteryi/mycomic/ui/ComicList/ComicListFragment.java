package me.masteryi.mycomic.ui.ComicList;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.databinding.FragmentComicListBinding;

/**
 * @author master.yi
 * @date 2017/1/5
 * @blog masteryi.me
 */

public class ComicListFragment extends BaseComicListFragment<ComicListPresenter> {
    private static final String ORDER = "1";
    public static final String URL = "url";
    private FragmentComicListBinding mBinding;
    private String mUrl;

    public ComicListFragment () {
    }

    public static ComicListFragment newInstance (String url) {
        ComicListFragment fragment = new ComicListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void getExtra (Bundle bundle) {
        mUrl = bundle.getString(URL);
    }

    @Override
    public void onGetDataByPageFinish () {
        // TODO: 2017/1/5
    }

    @Override
    protected View inflateView (LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_comic_list, container,
            false);
        return mBinding.getRoot();
    }

    @Override
    protected void initPresenter () {
        mPresenter = new ComicListPresenter(this);
    }

    @Override
    protected void initView () {
        mRecyclerView = mBinding.comicRecyclerView;
        mSwipeRefreshLayout = mBinding.swipeRefreshLayout;
        super.initView();
    }

    @Override
    protected String getOrder () {
        return ORDER;
    }

    @Override
    protected void getComic () {
        mPresenter.getComic(mUrl);
    }
}
