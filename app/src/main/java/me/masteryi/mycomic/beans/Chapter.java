package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 漫画章节
 *
 * @author master.yi
 * @date 2016/12/11
 * @blog masteryi.me
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Chapter {
    private String mTitle;
    private String mUrl;

    public Chapter () {
    }

    public String getTitle () {
        return mTitle;
    }

    public void setTitle (String title) {
        this.mTitle = title;
    }

    public String getUrl () {
        return mUrl;
    }

    public void setUrl (String url) {
        this.mUrl = url;
    }
}
