package me.masteryi.mycomic.utils.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orhanobut.logger.Logger;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author master.yi
 * @date 2016/11/21
 * @blog masteryi.me
 */

public class ComicService {
    private static ComicApi sInstance;

    public static ComicApi getApiInstance () {
        if(sInstance == null) {
            synchronized (ComicService.class) {
                if(sInstance == null) {
                    //OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(
                    //    new Interceptor() {
                    //        @Override
                    //        public Response intercept (Chain chain) throws IOException {
                    //            {
                    //                Request request = chain.request();
                    //                Response response = chain.proceed(request);
                    //                String msg = response.request().method() +
                    //                             " " + response.code() +
                    //                             " " + response.message() +
                    //                             " " + response.request().url();
                    //                Logger.i(msg);
                    //                return response;
                    //            }
                    //        }
                    //    }).build();
                    OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(
                        new StethoInterceptor()).addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept (Chain chain) throws IOException {
                            {
                                Request request = chain.request();
                                Response response = chain.proceed(request);
                                String msg = response.request().method() +
                                             " " +
                                             response.code() +
                                             " " +
                                             response.message() +
                                             " " +
                                             response.request().url();
                                Logger.i(msg);
                                return response;
                            }
                        }
                    }).build();

                    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                                                              .client(client)
                                                              .addCallAdapterFactory(
                                                                  RxJava2CallAdapterFactory.create())
                                                              .addConverterFactory(
                                                                  ScalarsConverterFactory.create())
                                                              .addConverterFactory(
                                                                  JacksonConverterFactory.create())
                                                              .build();

                    sInstance = retrofit.create(ComicApi.class);
                }
            }
        }
        return sInstance;
    }
}
