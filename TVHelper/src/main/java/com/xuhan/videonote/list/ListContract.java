package com.xuhan.videonote.list;

import com.xuhan.videonote.entity.LocalMediaEntity;
import com.xuhan.videonote.mvp.BasePresenter;
import com.xuhan.videonote.mvp.BaseView;

import java.util.List;

/**
 * @author meanhan
 */

public class ListContract {
    interface View extends BaseView {
        /**
         * 加载数据成功回调
         * @param mediaList
         */
        void loadSuccess(List<LocalMediaEntity> mediaList);

        /**
         * 数据加载失败回调
         * @param message
         */
        void loadFailed(String message);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 加载数据方法
         */
        void loadData();
    }
}
