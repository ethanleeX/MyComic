package me.masteryi.mycomic.comicdetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseAdapter;
import me.masteryi.mycomic.base.ViewHolderBinder;
import me.masteryi.mycomic.databinding.ComicDetailItemBinding;
import me.masteryi.mycomic.model.beans.ComicDetail;

/**
 * @author master.yi
 * @date 2016/12/18
 * @blog masteryi.me
 */

public class ComicDetailAdapter extends BaseAdapter {
    private ComicDetail mComicDetail;

    public ComicDetailAdapter (Context context) {
        super(context);
    }

    @Override
    public ViewHolderBinder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                                  .inflate(R.layout.comic_detail_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount () {
        return mComicDetail == null ? 0 : mComicDetail.getPageCount();
    }

    public void updateData (ComicDetail comicDetail) {
        mComicDetail = comicDetail;
        notifyDataSetChanged();
    }

    /**
     * 清除数据
     */
    public void clearData () {
        mComicDetail = new ComicDetail();
    }

    private class ItemViewHolder extends ViewHolderBinder {

        ComicDetailItemBinding mComicDetailItemBinding;

        public ItemViewHolder (View itemView) {
            super(itemView);
            mComicDetailItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void onBindViewHolder (int position) {
            String imageUrl = mComicDetail.getImages()[position];
            mComicDetailItemBinding.setImageUrl(imageUrl);
        }
    }
}
