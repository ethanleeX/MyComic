package me.masteryi.mycomic.ui.recommend;

import android.content.Context;
import android.content.Intent;
import me.masteryi.mycomic.beans.ComicCover;
import me.masteryi.mycomic.ui.comicintroduction.ComicIntroductionActivity;

/**
 * @author master.yi
 * @date 2016/12/10
 * @blog masteryi.me
 */

public class ClickHandler {
    private Context mContext;

    ClickHandler (Context context) {
        mContext = context;
    }

    public void onItemClick (ComicCover comicCover) {
        // TODO: 2016/12/10 共享元素动画
        Intent intent = new Intent(mContext, ComicIntroductionActivity.class);
        intent.putExtra(ComicIntroductionActivity.URL, comicCover.getUrl());
        intent.putExtra(ComicIntroductionActivity.TITLE, comicCover.getTitle());
        intent.putExtra(ComicIntroductionActivity.COVER, comicCover.getCoverImg());
        mContext.startActivity(intent);
    }
}
