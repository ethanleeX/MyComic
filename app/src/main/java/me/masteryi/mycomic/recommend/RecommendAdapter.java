package me.masteryi.mycomic.recommend;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.BR;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.beans.RecommendComic;
import me.masteryi.mycomic.databinding.RecommendListItemBinding;

/**
 * @author master.yi
 * @date 2016/12/3
 * @blog masteryi.me
 */

class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ItemViewHolder> {
    private List<RecommendComic> mRecommendComics = new ArrayList<>(4);
    private Context mContext;

    RecommendAdapter (Context context) {
        mContext = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        RecommendListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.recommend_list_item, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder (ItemViewHolder holder, int position) {
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.recommendComicList, mRecommendComics.get(position));
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                //TODO
            }
        });
        binding.executePendingBindings();
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

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private RecommendListItemBinding mBinding;

        ItemViewHolder (RecommendListItemBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mBinding = viewDataBinding;
        }

        RecommendListItemBinding getBinding () {
            return mBinding;
        }
    }
}
