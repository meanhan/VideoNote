package com.xuhan.videonote.list;

import com.xuhan.videonote.bean.MediaBean;
import com.xuhan.videonote.mvp.BasePresenter;
import com.xuhan.videonote.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 */

public class ListContract {
    interface View extends BaseView {
        void loadSuccess(List<MediaBean> mediaList);

        void loadFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();
    }
}
