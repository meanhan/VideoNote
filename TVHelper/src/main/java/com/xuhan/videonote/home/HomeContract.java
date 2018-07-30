package com.xuhan.videonote.home;

import com.xuhan.videonote.bean.VideoBean;
import com.xuhan.videonote.mvp.BasePresenter;
import com.xuhan.videonote.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 */

public class HomeContract {
    interface View extends BaseView {
        void loadSuccess(List<VideoBean> mVideoList);

        void loadFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();
    }
}
