package com.xuhan.videonote.movielistsubject;

import com.xuhan.videonote.entity.MovieEntity;
import com.xuhan.videonote.http.HttpManager;
import com.xuhan.videonote.mvp.BasePresenterImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author xuhan
 */

public class MovieListSubjectPresenter extends BasePresenterImpl<MovieListSubjectContract.View> implements MovieListSubjectContract.Presenter {

    @Override
    public void loadData() {
        mView.startLoad();
        HttpManager.getInstance(mView.getContext()).getComingSoonMovies(new Callback<MovieEntity>() {
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
