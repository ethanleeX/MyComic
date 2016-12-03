package me.masteryi.mycomic.beans;

import java.util.List;

/**
 * @author master.yi
 * @date 2016/11/28
 * @blog masteryi.me
 *
 * 推荐漫画
 */

public class RecommendComic {
    private static final int CATE_TOP = 0;
    private static final int CATE_FINISHED = 1;
    private static final int CATE_COLORFUL = 3;
    private static final int CATE_SALE = 4;

    private String mTitle;
    private List<ComicCover> mComicCovers;

    public RecommendComic (String title, List<ComicCover> comicCovers) {
        mTitle = title;
        mComicCovers = comicCovers;
    }

    public String getTitle () {
        return mTitle;
    }

    public void setTitle (String title) {
        mTitle = title;
    }

    public List<ComicCover> getComicCovers () {
        return mComicCovers;
    }

    public void setComicCovers (List<ComicCover> comicCovers) {
        mComicCovers = comicCovers;
    }
}
