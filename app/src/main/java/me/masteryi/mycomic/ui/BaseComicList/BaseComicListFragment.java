package me.masteryi.mycomic.ui.BaseComicList;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseFragment;
import me.masteryi.mycomic.beans.ComicCover;

/**
 * @author master.yi
 * @date 2017/1/2
 * @blog masteryi.me
 */

public abstract class BaseComicListFragment<P extends BaseComicListPresenter>
    extends BaseFragment<P> implements BaseComicListContract.IView {
    protected LinearLayoutManager mLayoutManager;
    protected BaseComicListAdapter mAdapter;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected RecyclerView mRecyclerView;
    private int mPage = 1;

    public BaseComicListFragment () {

    }

    @Override
    protected void initView () {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run () {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh () {
                mPage = 1;
                mPresenter.getComic();
            }
        });

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BaseComicListAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),
            DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        mRecyclerView.addItemDecoration(divider);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled (RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0 &&
                   mLayoutManager.findLastCompletelyVisibleItemPosition() ==
                   mAdapter.getItemCount() - 1) {
                    mPresenter.getComicByPage(mPage, getOrder());
                }
            }
        });
    }

    @Override
    protected void initData () {
        mPresenter.getComic();
    }

    protected abstract String getOrder ();

    @Override
    public void onGetDataSuccess (List<ComicCover> comicCovers) {
        mAdapter.update(comicCovers);
        mPage++;
    }

    @Override
    public void onGetDataFailure (Throwable t) {
        showErrorMsg(t);
    }

    @Override
    public void onGetDataFinish () {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetDataByPageSuccess (List<ComicCover> comicCovers) {
        mAdapter.addData(comicCovers);
        mPage++;
    }

    @Override
    public void onGetDataByPageFailure (Throwable t) {
        showErrorMsg(t);
    }
}

