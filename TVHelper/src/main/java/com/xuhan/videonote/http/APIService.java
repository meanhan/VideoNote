package com.xuhan.videonote.http;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by xuhan on 17-10-30.
 */

public interface APIService {

    @GET("categories")
    Call<String> getCategory();

    @GET("categories/contents")
    Call<String> getCategoryContent();

    @GET("contents/public")
    Call<String> getBookContent();
}
