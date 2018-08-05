package com.xuhan.videonote.mvp;

import android.content.Context;

/**
 * @author meanhan
 */

public interface BaseView {
    /**
     * 获取上下文
     * @return Context
     */
    Context getContext();

    /**
     * 获取布局ID
     * @return layoutId
     */
    int getLayoutId();

    /**
     *初始化视图
     */
    void initView();

    /**
     *初始化数据
     */
    void initData();

    /**
     * 初始化Listener
     */
    void initListener();
}
