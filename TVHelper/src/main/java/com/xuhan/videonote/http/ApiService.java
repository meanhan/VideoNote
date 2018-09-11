package com.xuhan.videonote.http;

import com.xuhan.videonote.entity.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author xuhan on 17-10-30.
 */

public interface ApiService {

    /**
     * 获取正在热映电影信息
     *
     * @return MovieEntity
     */
    @GET("in_theaters")
    Call<MovieEntity> getInTheatersMovies();

    /**
     * 获取即将上映电影信息
     *
     * @return MovieEntity
     */
    @GET("coming_soon")
    Call<MovieEntity> getComingSoonMovies();

    /**
     * 获取Top250电影信息
     *
     * @return MovieEntity
     */
    @GET("top250")
    Call<MovieEntity> getTopMovies();

    /**
     * 获取更多Top250电影信息
     *
     * @param start 开始位置
     * @param count 加载数量
     * @return
     */
    @GET("top250")
    Call<MovieEntity> getTopMoviesMore(@Query("start") String start, @Query("count") String count);

    /**
     * 获取口碑榜电影信息
     *
     * @return
     */
    @GET("weekly")
    Call<MovieEntity> getWeeklyMovies();

    /**
     * 获取北美票房榜电影信息
     *
     * @return
     */
    @GET("us_box")
    Call<MovieEntity> getUsBoxMovies();

    /**
     * 获取新片榜电影信息
     *
     * @return
     */
    @GET("new_movies")
    Call<MovieEntity> getNewMovies();

}
