package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

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
    @JsonIgnore
    private List<ComicPageDetail> mComicPageDetails;

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
        mComicPageDetails = new ArrayList<>(mImages.length);
        for(int i = 0; i < mImages.length; i++) {
            ComicPageDetail comicPageDetail = new ComicPageDetail(mImages[i], i, mPageCount);
            mComicPageDetails.add(comicPageDetail);
        }
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
        for(ComicPageDetail comicPageDetail : mComicPageDetails) {
            comicPageDetail.setChapterName(title);
        }
    }

    public List<ComicPageDetail> getComicPageDetails () {
        return mComicPageDetails;
    }

    public void setComicPageDetails (List<ComicPageDetail> comicPageDetails) {
        mComicPageDetails = comicPageDetails;
    }
}
