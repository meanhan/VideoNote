package com.xuhan.videonote.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.HomeRecyclerAdapter;
import com.xuhan.videonote.bean.VideoBean;
import com.xuhan.videonote.mvp.MVPBaseFragment;

import java.util.List;

/**
 * MVPPlugin
 */

public class HomeFragment extends MVPBaseFragment<HomeContract.View, HomePresenter> implements HomeContract.View {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private HomeRecyclerAdapter mAdapter;
    private List<VideoBean> mVideoList;
    private FloatingActionButton mActionButton;
    private OnHomeFragmentListener mListener;

    public interface OnHomeFragmentListener {
        void onFragmentClick();
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
                break;
            case R.id.action_scan:
                break;
            case R.id.action_help:
                break;
            case R.id.action_settings:
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
        toolbar.inflateMenu(R.menu.menu_main);
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
    public void loadSuccess(List<VideoBean> dataList) {
        mVideoList = dataList;
        refreshView();
    }

    @Override
    public void loadFailed(String message) {

    }

    private void refreshView() {
        mAdapter.setDataList(mVideoList);
        mAdapter.notifyDataSetChanged();
    }
}
