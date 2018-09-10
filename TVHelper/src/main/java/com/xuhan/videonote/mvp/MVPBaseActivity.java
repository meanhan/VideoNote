package com.xuhan.videonote.mvp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xuhan.videonote.R;
import com.zhl.cbdialog.CBDialogBuilder;

import java.lang.reflect.ParameterizedType;


/**
 * @author meanhan
 */

public abstract class MVPBaseActivity<V extends BaseView, T extends BasePresenterImpl<V>> extends AppCompatActivity implements BaseView {
    public T mPresenter;
    private Dialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPresenter = getInstance(this, 1);
        mPresenter.attachView((V) this);
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadingDialog != null) {
            mLoadingDialog = null;
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    public <T> T getInstance(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new CBDialogBuilder(this, CBDialogBuilder.DIALOG_STYLE_PROGRESS_AVLOADING)
                    // 设置是否点击对话框以外的区域dismiss对话框
                    .setTouchOutSideCancelable(true)
                    // 设置对话框的动画样式
                    .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                    // 设置对话框位于屏幕的位置
                    .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_CENTER)
                    .setDialogBackground(R.color.color_transparent)
                    .create();
        }
        mLoadingDialog.show();
    }

    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }
}
