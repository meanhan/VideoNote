package com.xuhan.videonote.list;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.VideoListAdapter;
import com.xuhan.videonote.bean.MediaBean;
import com.xuhan.videonote.mvp.MVPBaseFragment;
import com.xuhan.videonote.player.PlayerActivity;
import com.zhl.cbdialog.CBDialogBuilder;

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
    private List<MediaBean> mediaList;
    private OnListFragmentListener mListener;
    private Dialog mDialog;

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
        mDialog = null;
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
                MediaBean media = mediaList.get(position);
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra("media", media);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadSuccess(List<MediaBean> list) {
//        dismissLoadingDialog();
        mediaList = list;
        mTextView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter.setDataList(mediaList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadFailed(String message) {
//        dismissLoadingDialog();
        mTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    public void showLoadingDialog() {
        mDialog = new CBDialogBuilder(getActivity(), CBDialogBuilder.DIALOG_STYLE_PROGRESS_AVLOADING)
                .setTouchOutSideCancelable(true) // 设置是否点击对话框以外的区域dismiss对话框
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM) // 设置对话框的动画样式
                .setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_CENTER)  // 设置对话框位于屏幕的位置
                .create();
        mDialog.show();
    }

    public void dismissLoadingDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    private void onFragmentClick() {
        if (mListener != null) {
            mListener.onListFragmentClick();
        }
    }

}
