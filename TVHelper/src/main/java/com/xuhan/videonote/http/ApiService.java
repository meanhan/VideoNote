package com.xuhan.videonote.http;

import com.xuhan.videonote.bean.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author  xuhan on 17-10-30.
 */

public interface ApiService {

    /**
     * 获取正在热映电影信息
     * @return
     */
    @GET("in_theaters")
    Call<MovieEntity> getInTheatersMovie();

}
