package com.xuhan.videonote.personalcenter;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xuhan.videonote.R;
import com.xuhan.videonote.mvp.MVPBaseFragment;

/**
 * MVPPlugin
 */

public class PersonalCenterFragment extends MVPBaseFragment<PersonalCenterContract.View, PersonalCenterPresenter> implements PersonalCenterContract.View {

    private static final String ARG_PARAM = "param";
    private String mParam;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private ImageView mToolbarBg;
    private ImageView mToolbarIcon;
    private RecyclerView mRecyclerView;

    private OnPersonalFragmentListener mListener;

    public interface OnPersonalFragmentListener {
        void onPersonalFragmentClick();
    }

    public static PersonalCenterFragment newInstance(String param) {
        PersonalCenterFragment fragment = new PersonalCenterFragment();
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
        if (context instanceof OnPersonalFragmentListener) {
            mListener = (OnPersonalFragmentListener) context;
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
        return R.layout.fragment_me;
    }

    @Override
    public void initView() {
        mToolbar = fragmentView.findViewById(R.id.me_toolbar);
        mCollapsingToolbar = fragmentView.findViewById(R.id.me_collapsing_toolbar);
        mToolbarBg = fragmentView.findViewById(R.id.me_toolbar_bg);
        mToolbarIcon = fragmentView.findViewById(R.id.me_toolbar_icon);

        mCollapsingToolbar.setTitle(getString(R.string.my));
        Glide.with(this).load(R.drawable.image01).into(mToolbarBg);
        Glide.with(this).load(R.drawable.image02).into(mToolbarIcon);
        ((AppCompatActivity) getActivity()).setSupportActionBar(null);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    private void onFragmentClick() {
        if (mListener != null) {
            mListener.onPersonalFragmentClick();
        }
    }
}
