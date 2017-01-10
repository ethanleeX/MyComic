package me.masteryi.mycomic.ui.recommend;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseAdapter;
import me.masteryi.mycomic.base.ViewHolderBinder;
import me.masteryi.mycomic.beans.ComicCover;
import me.masteryi.mycomic.beans.RecommendComic;
import me.masteryi.mycomic.constant.IntentExtraKey;
import me.masteryi.mycomic.databinding.RecommendCoverItemBinding;
import me.masteryi.mycomic.databinding.RecommendTitleItemBinding;
import me.masteryi.mycomic.ui.comicintroduction.ComicIntroductionActivity;

/**
 * @author master.yi
 * @date 2016/12/3
 * @blog masteryi.me
 */

class RecommendAdapter extends BaseAdapter {
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_ITEM = 1;
    public static final int COVER_COUNT_ONE_LINE = 3;//一行的漫画数
    private List<RecommendComic> mRecommendComics = new ArrayList<>(4);
    private List<Pair<Integer, Object>> mItems = new ArrayList<>(28);

    RecommendAdapter (Context context) {
        super(context);
    }

    @Override
    public ViewHolderBinder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_TITLE:
                view = LayoutInflater.from(mContext)
                                     .inflate(R.layout.recommend_title_item, parent, false);
                return new TitleViewHolder(view);
            case TYPE_ITEM:
                view = LayoutInflater.from(mContext)
                                     .inflate(R.layout.recommend_cover_item, parent, false);
                return new ItemViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount () {
        return mItems.size();
    }

    @Override
    public int getItemViewType (int position) {
        return mItems.get(position).first;
    }

    void initData (List<RecommendComic> recommendComics) {
        mRecommendComics.clear();
        mRecommendComics.addAll(recommendComics);
        update();
    }

    private void update () {
        mItems.clear();
        for(RecommendComic recommendComic : mRecommendComics) {
            mItems.add(new Pair<Integer, Object>(TYPE_TITLE, recommendComic.getRecommendType()));
            List<ComicCover> comicCovers = recommendComic.getComicCovers()
                                                         .get(recommendComic.getCurrentPos());
            for(ComicCover cover : comicCovers) {
                mItems.add(new Pair<Integer, Object>(TYPE_ITEM, cover));
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 换一批
     */
    private void changeCoverList (String type) {
        for(RecommendComic recommendComic : mRecommendComics) {
            if(recommendComic.getRecommendType().equals(type)) {
                int currentPos = recommendComic.getCurrentPos();
                currentPos = ++currentPos % recommendComic.getComicCovers().size();
                recommendComic.setCurrentPos(currentPos);
                update();
            }
        }
    }

    private class TitleViewHolder extends ViewHolderBinder {
        RecommendTitleItemBinding mBinding;

        public TitleViewHolder (View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void onBindViewHolder (final int position) {
            mBinding.setRecommendType((String) mItems.get(position).second);
            mBinding.executePendingBindings();
            mBinding.change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    changeCoverList((String) mItems.get(position).second);
                }
            });
        }
    }

    private class ItemViewHolder extends ViewHolderBinder {
        private RecommendCoverItemBinding mBinding;

        ItemViewHolder (View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }

        @Override
        public void onBindViewHolder (int position) {
            final ComicCover comicCover = (ComicCover) mItems.get(position).second;
            mBinding.setComicCover(comicCover);
            mBinding.executePendingBindings();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    Intent intent = new Intent(mContext, ComicIntroductionActivity.class);
                    intent.putExtra(IntentExtraKey.URL, comicCover.getUrl());
                    intent.putExtra(IntentExtraKey.TITLE, comicCover.getTitle());
                    intent.putExtra(IntentExtraKey.COVER, comicCover.getCoverImg());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
