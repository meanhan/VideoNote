package com.xuhan.videonote.homepage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private View mRootView;
    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private OnFragmentInteractionListener mListener;
    private HomeRecyclerAdapter mAdapter;
    private List<VideoBean> mVideoList = new ArrayList<>();
    private FloatingActionButton mActionButton;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        toolbar = mRootView.findViewById(R.id.toolbar);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        mRecyclerView = mRootView.findViewById(R.id.home_recycler_view);
        mActionButton = mRootView.findViewById(R.id.fab);
        initDatas();
        bindListener();
        return mRootView;
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


    private void initDatas(){
        mVideoList.add(new VideoBean("楚乔传","10"));
        mVideoList.add(new VideoBean("楚乔传","10"));
        mVideoList.add(new VideoBean("楚乔传","10"));
        mVideoList.add(new VideoBean("楚乔传","10"));
        mVideoList.add(new VideoBean("楚乔传","10"));
        mVideoList.add(new VideoBean("楚乔传","10"));
        mVideoList.add(new VideoBean("楚乔传","10"));
        mVideoList.add(new VideoBean("楚乔传","10"));
        mVideoList.add(new VideoBean("楚乔传","10"));
        mAdapter = new HomeRecyclerAdapter(mVideoList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void bindListener() {
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "bar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public void onClick(View v) {
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
