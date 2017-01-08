package me.masteryi.mycomic.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 保存上一章/下一章章节信息
 *
 * @author master.yi
 * @date 2016/12/18
 * @blog masteryi.me
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NextChapterInfo {
    /**
     * s : 1
     * p : 369596
     * n : 372574
     * pt : 第179话 故人之意
     * nt : 第181话 结盟
     * i : 3670
     * b : 斗破苍穹
     * x : 第180话 厄难之毒
     */
    @JsonProperty("p")
    private String mPreviousId;
    @JsonProperty("n")
    private String mNextId;
    @JsonProperty("pt")
    private String mPreviousTitle;
    @JsonProperty("nt")
    private String mNextTitle;
    @JsonProperty("i")
    private String mId;//漫画id
    @JsonProperty("b")
    private String mName;
    @JsonProperty("x")
    private String mTitle;

    public NextChapterInfo () {
    }

    public String getPreviousId () {
        return mPreviousId;
    }

    public void setPreviousId (String previousId) {
        mPreviousId = previousId;
    }

    public String getNextId () {
        return mNextId;
    }

    public void setNextId (String nextId) {
        mNextId = nextId;
    }

    public String getPreviousTitle () {
        return mPreviousTitle;
    }

    public void setPreviousTitle (String previousTitle) {
        mPreviousTitle = previousTitle;
    }

    public String getNextTitle () {
        return mNextTitle;
    }

    public void setNextTitle (String nextTitle) {
        mNextTitle = nextTitle;
    }

    public String getId () {
        return mId;
    }

    public void setId (String id) {
        mId = id;
    }

    public String getName () {
        return mName;
    }

    public void setName (String name) {
        mName = name;
    }

    public String getTitle () {
        return mTitle;
    }

    public void setTitle (String title) {
        mTitle = title;
    }
}
