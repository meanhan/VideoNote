package com.xuhan.videonote.application;

import android.app.Application;

import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.xuhan.videonote.moment.boxing.BoxingGlideLoader;

/**
 * @author  meanhan on 2017/5/6.
 */

public class HomeApplication extends Application {

    public HomeApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化 boxing选择器
        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);
    }
}
