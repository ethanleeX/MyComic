package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * @author master.yi
 * @date 2016/12/11
 * @blog masteryi.me
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicIntroductionDetail {
    private String mCoverImg;//封面图
    private String mName;//作品名
    private String mAuthor;//作者
    private String mType;//类型
    private String mLastUpdateTime;//最后更新于
    private String mIntroduction;//简介
    private List<Chapter> mChapters;//章节列表

    public ComicIntroductionDetail () {
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

    public String getAuthor () {
        return mAuthor;
    }

    public void setAuthor (String author) {
        mAuthor = author;
    }

    public String getType () {
        return mType;
    }

    public void setType (String type) {
        mType = type;
    }

    public String getLastUpdateTime () {
        return mLastUpdateTime;
    }

    public void setLastUpdateTime (String lastUpdateTime) {
        mLastUpdateTime = lastUpdateTime;
    }

    public String getIntroduction () {
        return mIntroduction;
    }

    public void setIntroduction (String introduction) {
        mIntroduction = introduction;
    }

    public List<Chapter> getChapters () {
        return mChapters;
    }

    public void setChapters (List<Chapter> chapters) {
        mChapters = chapters;
    }
}
