package com.xuhan.videonote.mvp;

import android.content.Context;

/**
 * MVPPlugin
 */

public interface BaseView {
     Context getContext();
     int getLayoutId();
     void initView();
     void initData();
     void initListener();
}
