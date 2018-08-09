package com.xuhan.videonote.home;

import com.xuhan.videonote.entity.MovieEntity;
import com.xuhan.videonote.mvp.BasePresenter;
import com.xuhan.videonote.mvp.BaseView;

import java.util.List;

/**
 * @author meanhan
 */

public class HomeContract {
    interface View extends BaseView {
        /**
         * 加载数据成功回调
         * @param mVideoList
         */
        void loadSuccess(List<MovieEntity.SubjectsEntity> mVideoList);

        /**
         * 数据加载失败回调
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
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 加载数据
         */
        void loadData();
    }
}
