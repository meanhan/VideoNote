package com.xuhan.videonote.discover;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.ListRecyclerAdapter;
import com.xuhan.videonote.mvp.MVPBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meanhan
 */

public class DiscoverFragment extends MVPBaseFragment<DiscoverContract.View, DiscoverPresenter> implements DiscoverContract.View {

    private static final String ARG_PARAM = "param";
    private String mParam;
    private OnDiscoverFragmentListener mListener;
    private RecyclerView mRecyclerView;
    private ListRecyclerAdapter mAdapter;
    private List<String> mDataList;

    public interface OnDiscoverFragmentListener {
        /**
         * Fragment点击事件
         */
        void onDiscoverFragmentClick();
    }

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance(String param) {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDiscoverFragmentListener) {
            mListener = (OnDiscoverFragmentListener) context;
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
    public int getLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initView() {
        mRecyclerView = fragmentView.findViewById(R.id.discover_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ListRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.clear();
        mDataList.add("正在热映");
        mDataList.add("即将上映");
        mDataList.add("Top250");
        mDataList.add("口碑榜");
        mDataList.add("北美票房榜");
        mDataList.add("新片榜");
        refreshView();
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new ListRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), mDataList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onFragmentClick() {
        if (mListener != null) {
            mListener.onDiscoverFragmentClick();
        }
    }

    private void refreshView() {
        mAdapter.setDataList(mDataList);
        mAdapter.notifyDataSetChanged();
    }
}
