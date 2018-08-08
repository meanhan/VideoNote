package com.xuhan.videonote.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.DiscreteScrollViewAdapter;
import com.xuhan.videonote.bean.MovieEntity;
import com.xuhan.videonote.mvp.MVPBaseFragment;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meanhan
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View {

    //    private RecyclerView mRecyclerView;
//    private ListRecyclerAdapter mAdapter;
    private List<MovieEntity.SubjectsEntity> mMovieList = new ArrayList<>();
    private DiscreteScrollView mScrollView;
    private FloatingActionButton mActionButton;
    private OnHomeFragmentListener mListener;
    private InfiniteScrollAdapter mInfiniteAdapter;
    private DiscreteScrollViewAdapter mScrollAdapter;


    public interface OnHomeFragmentListener {
        /**
         * Fragment点击事件接口
         */
        void onHomeFragmentClick();
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnHomeFragmentListener) {
            mListener = (OnHomeFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * 设置 OptionsMenu显示图标
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_searcher:
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_scan:
                Toast.makeText(getActivity(), "扫一扫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_help:
                Toast.makeText(getActivity(), "帮助", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(getActivity(), "设置", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        setHasOptionsMenu(true);
        mScrollView = fragmentView.findViewById(R.id.item_picker);
        mActionButton = fragmentView.findViewById(R.id.item_btn_refresh);
        mScrollView.setOrientation(DSVOrientation.HORIZONTAL);
        mScrollAdapter = new DiscreteScrollViewAdapter(mMovieList);
        mInfiniteAdapter = InfiniteScrollAdapter.wrap(mScrollAdapter);
        mScrollView.setAdapter(mInfiniteAdapter);
        mScrollView.setItemTransitionTimeMillis(150);
        mScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
    }

    @Override
    public void initData() {
        mPresenter.loadData();
    }

    @Override
    public void initListener() {
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.loadData();
            }
        });
        mScrollView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

            }
        });
    }

    @Override
    public void loadSuccess(List<MovieEntity.SubjectsEntity> dataList) {
        if (dataList != null) {
            mMovieList = dataList;
            refreshView();
        }
    }

    @Override
    public void loadFailed(String message) {
        Toast.makeText(getActivity(), "加载失败...", Toast.LENGTH_SHORT).show();
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
        mScrollAdapter.setData(mMovieList);
        mScrollAdapter.notifyDataSetChanged();
    }
}
