package com.xuhan.videonote.movielistsubject;

import com.xuhan.videonote.entity.MovieEntity;
import com.xuhan.videonote.mvp.BasePresenter;
import com.xuhan.videonote.mvp.BaseView;

import java.util.List;

/**
 * @author xuhan
 */

public class MovieListSubjectContract {
    interface View extends BaseView {
        /**
         * 加载数据成功回调
         *
         * @param mVideoList
         */
        void loadSuccess(List<MovieEntity.SubjectsEntity> videoList);

        /**
         * 数据加载失败回调
         *
         * @param message
         */
        void loadFailed(String message);

        /**
         * 开始加载
         */
        void startLoad();

        /**
         * 结束加载
         */
        void endLoad();

        /**
         * 加载更多成功
         */
        void loadSuccessMore(List<MovieEntity.SubjectsEntity> videoList);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 加载正在热映
         */
        void loadInTheatersMovies();

        /**
         * 加载即将上映
         */
        void loadComingSoonMovies();

        /**
         * 加载Top250,默认加载20条
         */
        void loadTopMovies();

        /**
         * 加载更多Top250
         *
         * @param start 开始位置
         * @param count 加载数量
         */
        void loadTopMoviesMore(int start, int count);
    }
}
