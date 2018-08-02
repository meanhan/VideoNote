package com.xuhan.videonote.home;

import android.util.Log;

import com.xuhan.videonote.bean.MovieEntity;
import com.xuhan.videonote.bean.VideoBean;
import com.xuhan.videonote.http.HttpManager;
import com.xuhan.videonote.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MVPPlugin
 */

public class HomePresenter extends BasePresenterImpl<HomeContract.View> implements HomeContract.Presenter {

    @Override
    public void loadData() {
        mView.startLoad();
        HttpManager.getInstance(mView.getContext()).getInTheatersMovie(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                mView.endLoad();
                MovieEntity movieEntity = response.body();
                if (mView != null) {
                    mView.loadSuccess(movieEntity.getSubjects());
                }
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                mView.endLoad();
                if (mView != null) {
                    mView.loadFailed(t.toString());
                }
            }
        });
    }
}
