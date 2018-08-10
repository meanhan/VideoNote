package com.xuhan.videonote.movielistsubject;


import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.MovieSubjectAdapter;
import com.xuhan.videonote.contants.Contants;
import com.xuhan.videonote.entity.MovieEntity;
import com.xuhan.videonote.mvp.MVPBaseActivity;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xuhan
 *         显示电影榜单详情 : 正在热映/即将上映/Top250/口碑榜/北美票房榜/新片榜
 */

public class MovieListSubjectActivity extends
        MVPBaseActivity<MovieListSubjectContract.View, MovieListSubjectPresenter> implements
        MovieListSubjectContract.View {

    private List<MovieEntity.SubjectsEntity> mDataList = new ArrayList<>();
    private MovieSubjectView mMovieView;
    private DiscreteScrollView mMoviePicker;
    private MovieSubjectAdapter mAdapter;
    private ImageView mBtnBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_subject;
    }

    @Override
    public void initView() {
        mBtnBack = findViewById(R.id.btn_back);
        mMovieView = findViewById(R.id.forecast_view);
        mMoviePicker = findViewById(R.id.forecast_city_picker);
        mMoviePicker.setSlideOnFling(true);
        mAdapter = new MovieSubjectAdapter(mDataList);
        mMoviePicker.setAdapter(mAdapter);
        mMoviePicker.setItemTransitionTimeMillis(150);
        mMoviePicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
    }

    @Override
    public void initData() {
        String type = getIntent().getStringExtra(Contants.INTENT_MOVIE_LIST_TYPE);
        if ("正在热映".equals(type)) {
            mPresenter.loadInTheatersMovies();
        } else if ("即将上映".equals(type)) {
            mPresenter.loadComingSoonMovies();
        } else if ("Top250".equals(type)) {
            mPresenter.loadTopMovies();
        }
    }

    @Override
    public void initListener() {
        mMoviePicker.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<MovieSubjectAdapter.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable MovieSubjectAdapter.ViewHolder viewHolder, int adapterPosition) {
                if (viewHolder != null) {
                    mMovieView.setMovie(mDataList.get(adapterPosition));
                }
            }
        });

        mMoviePicker.addScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScrollStart(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

            }

            @Override
            public void onScrollEnd(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

            }

            @Override
            public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {
                MovieEntity.SubjectsEntity current = mDataList.get(currentPosition);
                if (newPosition >= 0 && newPosition < mMoviePicker.getAdapter().getItemCount()) {
                    MovieEntity.SubjectsEntity next = mDataList.get(newPosition);
                    mMovieView.onScroll(1f - Math.abs(scrollPosition), current, next);
                }
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void loadSuccess(List<MovieEntity.SubjectsEntity> dataList) {
        if (dataList != null) {
            mDataList = dataList;
            refreshView();
        }
    }

    @Override
    public void loadFailed(String message) {
        Toast.makeText(this, "加载失败...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startLoad() {
        showLoadingDialog();
    }

    @Override
    public void endLoad() {
        dismissLoadingDialog();
    }

    private void refreshView() {
        mAdapter.setData(mDataList);
        mAdapter.notifyDataSetChanged();
        mMoviePicker.scrollToPosition(2);
    }


}
