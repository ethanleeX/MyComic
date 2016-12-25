package me.masteryi.mycomic.comicdetail;

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
import me.masteryi.mycomic.databinding.ComicDetailItemBinding;
import me.masteryi.mycomic.model.beans.ComicDetail;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author master.yi
 * @date 2016/12/18
 * @blog masteryi.me
 */

public class ComicDetailAdapter extends BaseAdapter {
    private List<ComicDetail> mComicDetails = new ArrayList<>();
    private String[] mImages;

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
        //int count = 0;
        //for(ComicDetail comicDetail : mComicDetails) {
        //    count += comicDetail.getPageCount() == null ? 0 : comicDetail.getPageCount();
        //}
        //return count;
        return ArrayUtils.getLength(mImages);
    }

    /**
     * 增加上一章/下一章内容
     *
     * @param comicDetail 漫画内容
     * @param isNextChapter true表示下一章 false表示下一章
     */
    public void updateData (ComicDetail comicDetail, boolean isNextChapter) {
        if(isNextChapter) {
            mComicDetails.add(comicDetail);
            mImages = ArrayUtils.addAll(mImages, comicDetail.getImages());
            notifyItemRangeInserted(ArrayUtils.getLength(mImages), comicDetail.getPageCount());
        } else {
            mComicDetails.add(0, comicDetail);
            mImages = ArrayUtils.addAll(comicDetail.getImages(), mImages);
            notifyItemRangeInserted(0, comicDetail.getPageCount());
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
            String imageUrl = mImages[position];
            mComicDetailItemBinding.setImageUrl(imageUrl);
        }
    }
}
