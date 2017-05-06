package com.xuhan.videonote;

import android.view.View;

/**
 * Created by meanhan on 2017/5/6.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void initView(View view);
}
