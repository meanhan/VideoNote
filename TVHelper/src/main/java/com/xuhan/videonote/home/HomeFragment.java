package com.xuhan.videonote.home;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.HomeRecyclerAdapter;
import com.xuhan.videonote.bean.MovieEntity;
import com.xuhan.videonote.mvp.MVPBaseFragment;

import java.util.List;

/**
 * MVPPlugin
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private HomeRecyclerAdapter mAdapter;
    private List<MovieEntity.SubjectsEntity> mMovieList;
    private FloatingActionButton mActionButton;
    private OnHomeFragmentListener mListener;


    public interface OnHomeFragmentListener {
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

    // 设置 OptionsMenu显示图标
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
        toolbar = fragmentView.findViewById(R.id.toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setTitle("热映电影");
        toolbar.setOverflowIcon(getActivity().getDrawable(R.drawable.icon_more_white));
        mActionButton = fragmentView.findViewById(R.id.fab);
        mRecyclerView = fragmentView.findViewById(R.id.home_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new HomeRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
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
                Toast.makeText(getActivity(), "bar", Toast.LENGTH_SHORT).show();
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
        mAdapter.setDataList(mMovieList);
        mAdapter.notifyDataSetChanged();
    }
}
