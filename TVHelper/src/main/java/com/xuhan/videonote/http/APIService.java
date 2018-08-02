package com.xuhan.videonote.http;

import com.xuhan.videonote.bean.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by xuhan on 17-10-30.
 */

public interface APIService {

    @GET("in_theaters")
    Call<MovieEntity> getInTheatersMovie();

    @GET("categories/contents")
    Call<String> getCategoryContent();

    @GET("contents/public")
    Call<String> getBookContent();
}
