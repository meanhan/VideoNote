package com.xuhan.videonote.personalcenter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.xuhan.videonote.R;
import com.xuhan.videonote.mvp.MVPBaseFragment;
import com.xuhan.videonote.moment.MomentsActivity;

/**
 * @author meanhan
 */

public class PersonalCenterFragment extends MVPBaseFragment<PersonalCenterContract.View, PersonalCenterPresenter> implements PersonalCenterContract.View {

    private static final String ARG_PARAM = "param";
    private String mParam;
    private OnPersonalFragmentListener mListener;
    private RelativeLayout mItemMomentLayout;


    public interface OnPersonalFragmentListener {
        /**
         * 点击事件
         */
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
        mItemMomentLayout = fragmentView.findViewById(R.id.item_moment_layout);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mItemMomentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MomentsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onFragmentClick() {
        if (mListener != null) {
            mListener.onPersonalFragmentClick();
        }
    }
}
