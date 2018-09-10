package com.xuhan.videonote.http;

import android.content.Context;

import com.xuhan.videonote.application.HomeApplication;
import com.xuhan.videonote.entity.MovieEntity;
import com.xuhan.videonote.contants.ApiContants;
import com.xuhan.videonote.utils.NetworkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author xuhan on 17-11-1.
 */

public class HttpManager {
    private static HttpManager mInstance;
    private final Retrofit mRetrofit;
    private final ApiService mApiService;

    private HttpManager(final Context context) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                cacheBuilder.maxAge(0, TimeUnit.SECONDS);
                cacheBuilder.maxStale(365, TimeUnit.DAYS);
                CacheControl cacheControl = cacheBuilder.build();
                if (!NetworkUtil.networkConnected(context)) {
                    request = request.newBuilder()
                            .cacheControl(cacheControl)
                            .build();
                }

                Response response = chain.proceed(request);
                if (NetworkUtil.networkConnected(context)) {
                    // 有网络时 设置缓存超时时间0个小时
                    int maxAge = 0;
                    return response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    return response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };
        //设置缓存路径
        File httpCacheDirectory = new File(context.getApplicationContext().getCacheDir(), "responses");
        //设置缓存 10M
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        //创建OkHttpClient，并添加拦截器和缓存代码
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache).build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiContants.DOUBAN_MOVIE_BASE_URL)
                .client(client)
                // 返回String
                .addConverterFactory(ScalarsConverterFactory.create())
                // 加入Gson转化
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static HttpManager getInstance() {
        if (mInstance == null) {
            synchronized (HttpManager.class) {
                if (mInstance == null) {
                    mInstance = new HttpManager(HomeApplication.getContext());
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取正在热映
     *
     * @param callback
     */
    public void getInTheatersMovies(Callback<MovieEntity> callback) {
        mApiService.getInTheatersMovies().enqueue(callback);
    }

    /**
     * 获取即将上映
     *
     * @param callback
     */
    public void getComingSoonMovies(Callback<MovieEntity> callback) {
        mApiService.getComingSoonMovies().enqueue(callback);
    }

    /**
     * 获取Top250
     *
     * @param callback
     */
    public void getTopMovies(Callback<MovieEntity> callback) {
        mApiService.getTopMovies().enqueue(callback);
    }


}
