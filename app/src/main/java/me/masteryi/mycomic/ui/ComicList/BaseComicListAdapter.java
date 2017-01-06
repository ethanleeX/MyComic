package me.masteryi.mycomic.ui.ComicList;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseAdapter;
import me.masteryi.mycomic.base.ViewHolderBinder;
import me.masteryi.mycomic.beans.ComicCover;
import me.masteryi.mycomic.databinding.RecentComicDetailItemBinding;
import me.masteryi.mycomic.ui.comicintroduction.ComicIntroductionActivity;

/**
 * @author master.yi
 * @date 2017/1/2
 * @blog masteryi.me
 */

public class BaseComicListAdapter extends BaseAdapter {
    private List<ComicCover> mComicCovers = new ArrayList<>();

    public BaseComicListAdapter (Context context) {
        super(context);
    }

    @Override
    public ViewHolderBinder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                                  .inflate(R.layout.recent_comic_detail_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount () {
        return mComicCovers.size();
    }

    public void update (List<ComicCover> comicCovers) {
        mComicCovers.clear();
        addData(comicCovers);
    }

    public void addData (List<ComicCover> comicCovers) {
        mComicCovers.addAll(comicCovers);
        notifyItemRangeInserted(mComicCovers.size(), comicCovers.size());
    }

    class ItemViewHolder extends ViewHolderBinder {
        RecentComicDetailItemBinding mItemBinding;

        public ItemViewHolder (View itemView) {
            super(itemView);
            mItemBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void onBindViewHolder (int position) {
            final ComicCover comicCover = mComicCovers.get(position);
            mItemBinding.setComicDetail(comicCover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Intent intent = new Intent(mContext, ComicIntroductionActivity.class);
                    intent.putExtra(ComicIntroductionActivity.URL, comicCover.getUrl());
                    intent.putExtra(ComicIntroductionActivity.TITLE, comicCover.getTitle());
                    intent.putExtra(ComicIntroductionActivity.COVER, comicCover.getCoverImg());
                    mContext.startActivity(intent);
                    Logger.d("comic url:" + comicCover.getUrl());
                }
            });
        }
    }
}

