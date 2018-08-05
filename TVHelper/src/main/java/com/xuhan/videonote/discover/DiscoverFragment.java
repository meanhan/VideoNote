package com.xuhan.videonote.discover;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuhan.videonote.R;
import com.xuhan.videonote.mvp.MVPBaseFragment;

/**
 * @author meanhan
 */

public class DiscoverFragment extends MVPBaseFragment<DiscoverContract.View, DiscoverPresenter> implements DiscoverContract.View {

    private static final String ARG_PARAM = "param";
    private String mParam;
    private OnDiscoverFragmentListener mListener;

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

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    public void onFragmentClick() {
        if (mListener != null) {
            mListener.onDiscoverFragmentClick();
        }
    }
}
