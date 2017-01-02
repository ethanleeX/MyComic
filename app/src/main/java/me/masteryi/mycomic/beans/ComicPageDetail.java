package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author master.yi
 * @date 2016/12/25
 * @blog masteryi.me
 *
 * 每页漫画信息
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicPageDetail {
    private String mImage;
    private Integer mPage;
    private String mChapterName;
    private Integer mTotalPageCount;

    public ComicPageDetail () {
    }

    public ComicPageDetail (String image, Integer page, int totalPageCount) {
        mImage = image;
        mPage = page;
        mTotalPageCount = totalPageCount;
    }

    public String getImage () {
        return mImage;
    }

    public void setImage (String image) {
        mImage = image;
    }

    public Integer getPage () {
        return mPage;
    }

    public void setPage (Integer page) {
        mPage = page;
    }

    public String getChapterName () {
        return mChapterName;
    }

    public void setChapterName (String chapterName) {
        mChapterName = chapterName;
    }

    public Integer getTotalPageCount () {
        return mTotalPageCount;
    }

    public void setTotalPageCount (Integer totalPageCount) {
        mTotalPageCount = totalPageCount;
    }
}
