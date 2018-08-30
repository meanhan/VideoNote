package com.xuhan.videonote.discover;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.DiscoverRecyclerAdapter;
import com.xuhan.videonote.adapter.MyDividerItemDecoration;
import com.xuhan.videonote.contants.Contants;
import com.xuhan.videonote.moment.MomentsActivity;
import com.xuhan.videonote.movielistsubject.MovieListSubjectActivity;
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
    private RelativeLayout mItemMomentLayout;
    private RecyclerView mRecyclerView;
    private DiscoverRecyclerAdapter mAdapter;
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
        mItemMomentLayout = fragmentView.findViewById(R.id.item_moment_layout);
        mRecyclerView = fragmentView.findViewById(R.id.discover_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new MyDividerItemDecoration() {
            @Override
            public Decoration getItemOffsets(int position) {
                ColorDecoration decoration = new ColorDecoration();
                decoration.bottom = 2;
                decoration.decorationColor = getResources().getColor(R.color.colorGrayLight);
                return decoration;
            }
        });
        mAdapter = new DiscoverRecyclerAdapter();
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
        mItemMomentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MomentsActivity.class);
                startActivity(intent);
            }
        });
        mAdapter.setOnItemClickListener(new DiscoverRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position < 3) {
                    Intent intent = new Intent(getActivity(), MovieListSubjectActivity.class);
                    intent.putExtra(Contants.INTENT_MOVIE_LIST_TYPE, mDataList.get(position));
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), mDataList.get(position), Toast.LENGTH_SHORT).show();
                }
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
