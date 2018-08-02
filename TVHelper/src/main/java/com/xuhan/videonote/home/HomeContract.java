package com.xuhan.videonote.home;

import com.xuhan.videonote.bean.MovieEntity;
import com.xuhan.videonote.mvp.BasePresenter;
import com.xuhan.videonote.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 */

public class HomeContract {
    interface View extends BaseView {
        void loadSuccess(List<MovieEntity.SubjectsEntity> mVideoList);

        void loadFailed(String message);

        void startLoad();

        void endLoad();
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();
    }
}
