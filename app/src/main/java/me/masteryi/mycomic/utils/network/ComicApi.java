package me.masteryi.mycomic.utils.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author master.yi
 * @date 2016/11/21
 * @blog masteryi.me
 */

public interface ComicApi {
    @GET(".")
    Observable<String> getHome ();//首页

    @GET("comic/quanbu/")
    Observable<String> getCategory ();//分类

    @GET("top.html")
    Observable<String> getTop ();//风云榜

    @GET
    Observable<String> getComicChapter (@Url String url);//获取章节列表
}

