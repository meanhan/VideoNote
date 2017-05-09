package com.xuhan.videonote.mvp;


/**
 * Created by meanhan on 2017/5/9.
 */

public interface ListContract {

    interface View extends BaseView<Presenter>{
        // 显示加载或其他类型的错误
        void showError();
        // 显示正在加载
        void showLoading();
        // 停止显示正在加载
        void stopLoading();
        // 成功获取到数据后，在界面中显示
        void showResults();
    }

    interface Presenter extends BasePresenter{
        // 请求数据
        void loadPosts();
        // 刷新数据
        void refresh();
        // 加载更多文章
        void loadMore();
    }
}
