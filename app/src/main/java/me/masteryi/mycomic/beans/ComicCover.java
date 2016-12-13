package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author master.yi
 * @date 2016/11/28
 * @blog masteryi.me
 *
 * 漫画封面
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicCover {
    private String mCoverImg;//封面图
    private String mName;//书名
    private String mLatestChapter;//最新章节
    private String mUrl;//链接

    public ComicCover () {
    }

    public ComicCover (String coverImg, String name, String latestChapter, String url) {
        mCoverImg = coverImg;
        mName = name;
        mLatestChapter = latestChapter;
        mUrl = url;
    }

    public String getCoverImg () {
        return mCoverImg;
    }

    public void setCoverImg (String coverImg) {
        mCoverImg = coverImg;
    }

    public String getName () {
        return mName;
    }

    public void setName (String name) {
        mName = name;
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

}
