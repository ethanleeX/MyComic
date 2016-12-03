package me.masteryi.mycomic.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.beans.ComicCover;
import me.masteryi.mycomic.beans.RecommendComic;

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
        View view = LayoutInflater.from(mContext)
                                  .inflate(R.layout.recommend_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder (ItemViewHolder holder, int position) {
        RecommendComic recommendComic = mRecommendComics.get(position);
        holder.mChange.setOnClickListener(view -> {
            //TODO
        });

        holder.mTitle.setText(recommendComic.getTitle());

        List<ComicCover> comicCovers = recommendComic.getComicCovers();
        for(int i = 0; i < comicCovers.size(); i++) {
            ComicCover comicCover = comicCovers.get(i);
            SimpleDraweeView coverView = (SimpleDraweeView) holder.mItemViews[i].findViewById(
                R.id.cover);
            TextView nameView = (TextView) holder.mItemViews[i].findViewById(R.id.name);
            TextView latestChapterView = (TextView) holder.mItemViews[i].findViewById(
                R.id.latest_chapter);

            coverView.setImageURI(comicCovers.get(i).getCoverImg());
            nameView.setText(comicCover.getName());
            latestChapterView.setText(comicCover.getLatestChapter());
        }
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
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.change)
        TextView mChange;
        @BindViews({R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6})
        View[] mItemViews;

        ItemViewHolder (View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
