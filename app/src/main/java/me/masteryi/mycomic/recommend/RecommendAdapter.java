package me.masteryi.mycomic.recommend;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.BR;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseAdapter;
import me.masteryi.mycomic.base.ViewHolderBinder;
import me.masteryi.mycomic.beans.RecommendComic;
import me.masteryi.mycomic.databinding.RecommendListItemBinding;

/**
 * @author master.yi
 * @date 2016/12/3
 * @blog masteryi.me
 */

class RecommendAdapter extends BaseAdapter {
    private List<RecommendComic> mRecommendComics = new ArrayList<>(4);

    RecommendAdapter (Context context) {
        super(context);
    }

    @Override
    public ViewHolderBinder onCreateViewHolder (ViewGroup parent, int viewType) {
        RecommendListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.recommend_list_item, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public int getItemCount () {
        return mRecommendComics.size();
    }

    void refreshData (List<RecommendComic> recommendComics) {
        mRecommendComics.clear();
        mRecommendComics.addAll(recommendComics);
        notifyDataSetChanged();
    }

    private class ItemViewHolder extends ViewHolderBinder {
        private RecommendListItemBinding mBinding;

        ItemViewHolder (RecommendListItemBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mBinding = viewDataBinding;
        }

        @Override
        public void onBindViewHolder (int position) {
            mBinding.setVariable(BR.recommendComicList, mRecommendComics.get(position));
            mBinding.setVariable(BR.handler, new ClickHandler(mContext));
            mBinding.executePendingBindings();
        }
    }
}
