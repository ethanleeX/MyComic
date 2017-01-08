package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 分类
 *
 * @author master.yi
 * @date 2017/1/4
 * @blog masteryi.me
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private String mName;
    private String mUrl;
    private Integer mResId;

    public Category () {
    }

    public Category (String name, String url, Integer resId) {
        mName = name;
        mUrl = url;
        mResId = resId;
    }

    public String getName () {
        return mName;
    }

    public void setName (String name) {
        mName = name;
    }

    public String getUrl () {
        return mUrl;
    }

    public void setUrl (String url) {
        mUrl = url;
    }

    public Integer getResId () {
        return mResId;
    }

    public void setResId (Integer resId) {
        mResId = resId;
    }
}
