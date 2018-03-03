package com.xuhan.videonote.homepage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.HomeRecyclerAdapter;
import com.xuhan.videonote.bean.VideoBean;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private View mRootView;
    private RecyclerView mRecyclerView;
    private OnFragmentInteractionListener mListener;
    private HomeRecyclerAdapter mAdapter;
    private List<VideoBean> mVideoList = new ArrayList<>();

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
        mRecyclerView = mRootView.findViewById(R.id.home_recycler_view);
        initDatas();
        bindListener();
        return mRootView;
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
