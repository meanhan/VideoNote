package com.xuhan.videonote.movielistsubject;

import android.util.Log;

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
    public void loadInTheatersMovies() {
        mView.startLoad();
        HttpManager.getInstance().getInTheatersMovies(new Callback<MovieEntity>() {
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

    @Override
    public void loadComingSoonMovies() {
        mView.startLoad();
        HttpManager.getInstance().getComingSoonMovies(new Callback<MovieEntity>() {
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

    @Override
    public void loadTopMovies() {
        mView.startLoad();
        HttpManager.getInstance().getTopMovies(new Callback<MovieEntity>() {
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

    @Override
    public void loadTopMoviesMore(int start, int count) {
        mView.startLoad();
        HttpManager.getInstance().getTopMoviesMore(start, count, new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                mView.endLoad();
                MovieEntity movieEntity = response.body();
                if (mView != null) {
                    mView.loadSuccessMore(movieEntity.getSubjects());
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
