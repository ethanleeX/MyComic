package me.masteryi.mycomic.recommend;

import android.content.Context;
import android.content.Intent;
import me.masteryi.mycomic.beans.ComicCover;
import me.masteryi.mycomic.comicintroduction.ComicIntroductionActivity;

/**
 * @author master.yi
 * @date 2016/12/10
 * @blog masteryi.me
 */

public class ClickHandler {
    Context mContext;

    ClickHandler (Context context) {
        mContext = context;
    }

    public void onItemClick (ComicCover comicCover) {
        // TODO: 2016/12/10
        Intent intent = new Intent(mContext, ComicIntroductionActivity.class);
        intent.putExtra(ComicIntroductionActivity.URL, comicCover.getUrl());
        intent.putExtra(ComicIntroductionActivity.NAME, comicCover.getName());
        intent.putExtra(ComicIntroductionActivity.COVER, comicCover.getCoverImg());
        mContext.startActivity(intent);
    }
}
