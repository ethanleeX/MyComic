package me.masteryi.mycomic.utils.network;

import io.reactivex.Observable;
import me.masteryi.mycomic.model.beans.ComicChapter;
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
     * 获得漫画页数
     *
     * @param comicId
     * @param chapterId
     * @return
     */
    @GET("manhua/{comicId}/{chapterId}.html")
    Observable<String> getComicDetailPageCount (@Path("comicId") String comicId,
                                                @Path("chapterId") String chapterId);

    /**
     * 获得漫画图片
     *
     * @param comicId
     * @param chapterId
     * @param page
     * @return
     */
    @GET("manhua/{comicId}/{chapterId}.html")
    Observable<String> getComicDetail (@Path("comicId") String comicId,
                                       @Path("chapterId") String chapterId,
                                       @Query("p") int page);

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
    @GET("act/?cb=jsonp1&bid={comicId}&cid={chapterId}")
    Observable<ComicChapter> getNextChapter (@Path("comicId") String comicId,
                                             @Path("chapterId") String chapterId);
}

