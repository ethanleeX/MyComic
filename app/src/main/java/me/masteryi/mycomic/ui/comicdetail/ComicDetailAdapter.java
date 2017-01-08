package me.masteryi.mycomic.ui.comicdetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseAdapter;
import me.masteryi.mycomic.base.ViewHolderBinder;
import me.masteryi.mycomic.beans.ComicContent;
import me.masteryi.mycomic.beans.ComicPageDetail;
import me.masteryi.mycomic.databinding.ComicDetailItemBinding;

/**
 * @author master.yi
 * @date 2016/12/18
 * @blog masteryi.me
 */

public class ComicDetailAdapter extends BaseAdapter {
    private List<ComicPageDetail> mComicPageDetails = new ArrayList<>();

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
        return mComicPageDetails.size();
    }

    /**
     * 增加上一章/下一章内容
     *
     * @param comicContent 漫画内容
     * @param isNextChapter true表示下一章 false表示下一章
     */
    public void updateData (ComicContent comicContent, boolean isNextChapter) {
        if(isNextChapter) {
            mComicPageDetails.addAll(comicContent.getComicPageDetails());
            notifyItemRangeInserted(mComicPageDetails.size(), comicContent.getPageCount());
        } else {
            mComicPageDetails.addAll(0, comicContent.getComicPageDetails());
            notifyItemRangeInserted(0, comicContent.getPageCount());
        }
    }

    private class ItemViewHolder extends ViewHolderBinder {

        ComicDetailItemBinding mComicDetailItemBinding;

        public ItemViewHolder (View itemView) {
            super(itemView);
            mComicDetailItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void onBindViewHolder (int position) {
            String imageUrl = mComicPageDetails.get(position).getImage();
            mComicDetailItemBinding.setImageUrl(imageUrl);
        }
    }

    public ComicPageDetail getItem (int position) {
        return mComicPageDetails.get(position);
    }
}
