package me.masteryi.mycomic.model.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author master.yi
 * @date 2016/12/18
 * @blog masteryi.me
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicDetail {
    @JsonIgnore
    private Integer mPageCount;
    @JsonIgnore
    private String[] mImages;//漫画内容
    @JsonIgnore
    private String title;

    public ComicDetail () {
    }

    public Integer getPageCount () {
        return mPageCount;
    }

    public void setPageCount (Integer pageCount) {
        mPageCount = pageCount;
        mImages = new String[pageCount];
    }

    public String[] getImages () {
        return mImages;
    }

    public void setImages (String[] images) {
        mImages = images;
    }

    public void setImage (int page, String imgUrl) {
        mImages[page] = imgUrl;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }
}
