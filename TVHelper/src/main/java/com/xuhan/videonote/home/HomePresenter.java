package com.xuhan.videonote.home;

import com.xuhan.videonote.bean.VideoBean;
import com.xuhan.videonote.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 */

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter {

    @Override
    public void loadData() {
        List<VideoBean> videoList = new ArrayList<>();
        videoList.add(new VideoBean("楚乔传", "10"));
        videoList.add(new VideoBean("楚乔传", "10"));
        videoList.add(new VideoBean("楚乔传", "10"));
        videoList.add(new VideoBean("楚乔传", "10"));
        videoList.add(new VideoBean("楚乔传", "10"));
        videoList.add(new VideoBean("楚乔传", "10"));
        videoList.add(new VideoBean("楚乔传", "10"));
        videoList.add(new VideoBean("楚乔传", "10"));
        videoList.add(new VideoBean("楚乔传", "10"));
        if (mView != null) {
            mView.loadSuccess(videoList);
        }
    }
}
