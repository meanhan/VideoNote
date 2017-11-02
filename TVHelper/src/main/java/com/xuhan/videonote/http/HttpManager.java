package com.xuhan.videonote.http;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by xuhan on 17-11-1.
 */

public class HttpManager {
    private static final String BASE_URL = "http://api.avatarmind.com/QueueServer/v1/";
    private static HttpManager mInstance;
    private final Retrofit mRetrofit;
    private final APIService mApiService;

    private HttpManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(APIService.class);
    }

    public static HttpManager getInstance(){
        if (mInstance == null)
        {
            synchronized (HttpManager.class)
            {
                if (mInstance == null)
                {
                    mInstance = new HttpManager();
                }
            }
        }
        return mInstance;
    }

    public void getCategory(Callback<String> callback){
        mApiService.getCategory().enqueue(callback);
    }

    public void getCategoryContent(Callback<String> callback){
        mApiService.getCategoryContent().enqueue(callback);
    }

    public void getBookContent(Callback<String> callback){
        mApiService.getBookContent().enqueue(callback);
    }

}
