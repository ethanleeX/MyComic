package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 漫画封面
 *
 * @author master.yi
 * @date 2016/11/28
 * @blog masteryi.me
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicCover {
    private String mCoverImg;//封面图
    private String mTitle;//书名
    private String mLatestChapter;//最新章节
    private String mUrl;//链接
    private String mUpdateTime;//更新时间
    private String mType;//类别
    private String mAuthor;//作者
    private String mState;//状态  连载中/完结

    public ComicCover () {
    }

    public ComicCover (String coverImg, String name, String latestChapter, String url) {
        mCoverImg = coverImg;
        mTitle = name;
        mLatestChapter = latestChapter;
        mUrl = url;
    }

    public String getCoverImg () {
        return mCoverImg;
    }

    public void setCoverImg (String coverImg) {
        mCoverImg = coverImg;
    }

    public String getTitle () {
        return mTitle;
    }

    public void setTitle (String title) {
        mTitle = title;
    }

    public String getLatestChapter () {
        return mLatestChapter;
    }

    public void setLatestChapter (String latestChapter) {
        mLatestChapter = latestChapter;
    }

    public String getUrl () {
        return mUrl;
    }

    public void setUrl (String url) {
        mUrl = url;
    }

    public String getUpdateTime () {
        return mUpdateTime;
    }

    public void setUpdateTime (String updateTime) {
        mUpdateTime = updateTime;
    }

    public String getType () {
        return mType;
    }

    public void setType (String type) {
        mType = type;
    }

    public String getAuthor () {
        return mAuthor;
    }

    public void setAuthor (String author) {
        mAuthor = author;
    }

    public String getState () {
        return mState;
    }

    public void setState (String state) {
        mState = state;
    }
}
