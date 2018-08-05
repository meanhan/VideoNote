package com.xuhan.videonote.mvp;

/**
 * @author
 */

public interface BasePresenter<V extends BaseView> {
    /**
     * 绑定View
     * @param view
     */
    void attachView(V view);

    /**
     * 解绑View
     */
    void detachView();
}
