package me.masteryi.mycomic.utils.network;

import com.orhanobut.logger.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author master.yi
 * @date 2016/11/21
 * @blog masteryi.me
 */

public class ComicService {
    private static ComicApi sInstance;

    public static ComicApi getApiInstance () {
        if (sInstance == null) {
            synchronized (ComicService.class) {
                if (sInstance != null) {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addNetworkInterceptor(chain -> {
                                Request request = chain.request();
                                Logger.d(request.url().toString());
                                Response response = chain.proceed(request);
                                Logger.d(response.code());
                                Logger.d(response.body().toString());
                                return response;
                            })
                            .build();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .client(client)
                            .addConverterFactory(JacksonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();

                    sInstance = retrofit.create(ComicApi.class);
                }
            }
        }
        return sInstance;
    }
}
