package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * 漫画介绍
 *
 * @author master.yi
 * @date 2016/12/11
 * @blog masteryi.me
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicIntroduction extends ComicCover {
    private String mIntroduction;//简介
    private List<Chapter> mChapters;//章节列表

    public ComicIntroduction () {
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
