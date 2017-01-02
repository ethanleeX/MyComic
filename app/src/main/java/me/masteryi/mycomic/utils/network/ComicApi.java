package me.masteryi.mycomic.utils.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author master.yi
 * @date 2016/11/21
 * @blog masteryi.me
 */

public interface ComicApi {
    /**
     * 首页
     *
     * @return
     */
    @GET(".")
    Observable<String> getHome ();

    /**
     * 更新
     *
     * @return
     */
    @GET("recent.html")
    Observable<String> getRecent ();

    /**
     * 分类
     *
     * @return
     */
    @GET("comic/quanbu/")
    Observable<String> getCategory ();

    /**
     * 风云榜
     *
     * @return
     */
    @GET("top.html")
    Observable<String> getTop ();

    /**
     * 获取章节列表
     *
     * @param url
     * @return
     */
    @GET
    Observable<String> getComicChapter (@Url String url);

    /**
     * 获得漫画图片
     *
     * @param comicId
     * @param chapterId
     * @return
     */
    @GET("manhua/{comicId}/{chapterId}.html")
    Observable<String> getComicDetail (@Path("comicId") String comicId,
                                       @Path("chapterId") String chapterId);

    /**
     * 获得漫画上一章/下一章详情
     *
     * @param comicId
     * @param chapterId
     * @return
     */
    @GET("act")
    Observable<String> getNextChapter (@Query("cb") String cb, @Query("bid") String comicId,
                                       @Query("cid") String chapterId);

    /**
     * 更新
     *
     * @return
     */
    @GET("act")
    Observable<String> getRecentByPage (@Query("act") String act, @Query("page") int page,
                                        @Query("catid") String catid, @Query("ajax") String ajax,
                                        @Query("order") String order);
}

