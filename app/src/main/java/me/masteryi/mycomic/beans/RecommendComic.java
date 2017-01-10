package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * 推荐漫画
 *
 * @author master.yi
 * @date 2016/11/28
 * @blog masteryi.me
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendComic {
    private String mRecommendType;
    private List<List<ComicCover>> mComicCovers;
    private Integer mCurrentPos;

    public RecommendComic (String recommendType, List<List<ComicCover>> comicCovers) {
        mRecommendType = recommendType;
        mComicCovers = comicCovers;
        mCurrentPos = 0;
    }

    public String getRecommendType () {
        return mRecommendType;
    }

    public void setRecommendType (String recommendType) {
        mRecommendType = recommendType;
    }

    public List<List<ComicCover>> getComicCovers () {
        return mComicCovers;
    }

    public void setComicCovers (List<List<ComicCover>> comicCovers) {
        mComicCovers = comicCovers;
    }

    public Integer getCurrentPos () {
        return mCurrentPos;
    }

    public void setCurrentPos (Integer currentPos) {
        mCurrentPos = currentPos;
    }
}
