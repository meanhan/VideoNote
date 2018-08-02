package com.xuhan.videonote.list;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.VideoListAdapter;
import com.xuhan.videonote.bean.LocalMediaEntity;
import com.xuhan.videonote.mvp.MVPBaseFragment;
import com.xuhan.videonote.player.PlayerActivity;
import com.zhl.cbdialog.pedant.SweetAlert.SweetAlertDialog;

import java.util.List;

/**
 * MVPPlugin
 */

public class ListFragment extends MVPBaseFragment<ListContract.View, ListPresenter> implements ListContract.View {

    private static final String ARG_PARAM = "param";
    private String mParam;
    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private VideoListAdapter mAdapter;
    private List<LocalMediaEntity> mediaList;
    private OnListFragmentListener mListener;
    private SweetAlertDialog mLoadingDialog;

    public interface OnListFragmentListener {
        void onListFragmentClick();
    }

    public static ListFragment newInstance(String param) {
        ListFragment fragment = new ListFragment();
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
        if (context instanceof OnListFragmentListener) {
            mListener = (OnListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        mLoadingDialog = null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView() {
        mRecyclerView = fragmentView.findViewById(R.id.recycler_view_list);
        mTextView = fragmentView.findViewById(R.id.tv_no_media);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new VideoListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        showLoadingDialog();
        mPresenter.loadData();
    }

    @Override
    public void initListener() {
        mAdapter.setItemClickListener(new VideoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LocalMediaEntity media = mediaList.get(position);
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra("media", media);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadSuccess(List<LocalMediaEntity> list) {
        mediaList = list;
        mTextView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter.setDataList(mediaList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadFailed(String message) {
        mTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingDialog() {
        mLoadingDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        mLoadingDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        mLoadingDialog.setTitleText("Loading");
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(true);
        mLoadingDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    private void onFragmentClick() {
        if (mListener != null) {
            mListener.onListFragmentClick();
        }
    }

}
