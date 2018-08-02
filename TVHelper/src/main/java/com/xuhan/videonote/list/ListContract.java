package com.xuhan.videonote.list;

import com.xuhan.videonote.bean.LocalMediaEntity;
import com.xuhan.videonote.mvp.BasePresenter;
import com.xuhan.videonote.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 */

public class ListContract {
    interface View extends BaseView {
        void loadSuccess(List<LocalMediaEntity> mediaList);

        void loadFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        void loadData();
    }
}
