package com.xuhan.videonote.application;

import android.app.Application;
import android.content.Context;

import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.loader.IBoxingMediaLoader;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.xuhan.videonote.moment.boxing.BoxingGlideLoader;

/**
 * @author meanhan on 2017/5/6.
 */

public class HomeApplication extends Application {

    private static Context mContext;
    private RefWatcher refWatcher;

    public HomeApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        // 初始化 boxing选择器
        IBoxingMediaLoader loader = new BoxingGlideLoader();
        BoxingMediaLoader.getInstance().init(loader);

        // 初始化Facebook Stetho
        Stetho.initializeWithDefaults(this);

        //初始化LeakCanary
        refWatcher = LeakCanary.install(this);
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 提供获取RefWatcher方法,可用于在Fragment中监控
     *
     * @param context
     * @return RefWatcher
     */
    public static RefWatcher getRefWatcher(Context context) {
        HomeApplication application = (HomeApplication) context.getApplicationContext();
        return application.refWatcher;
    }
}
