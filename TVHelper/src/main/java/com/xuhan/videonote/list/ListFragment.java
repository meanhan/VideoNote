package com.xuhan.videonote.list;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.VideoListAdapter;
import com.xuhan.videonote.bean.MediaBean;
import com.xuhan.videonote.mvp.ListContract;
import com.xuhan.videonote.player.PlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private static final String ARG_PARAM = "param";
    private String mParam;
    private TextView mTextView;
    private RecyclerView mRecyclerView;
    private VideoListAdapter mAdapter;
    private List<MediaBean> mediaList;
    private OnFragmentInteractionListener mListener;

    public ListFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = mainView.findViewById(R.id.recycler_view_list);
        mTextView = mainView.findViewById(R.id.tv_no_media);
        initRecyclerView();
        return mainView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void initRecyclerView(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new VideoListAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new VideoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MediaBean media = mediaList.get(position);
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra("media",media);
                startActivity(intent);
            }
        });
        scanMediaFromSDCard();
        refreshView();
    }

    private void scanMediaFromSDCard() {
        mediaList = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        // MediaStore.Video.Thumbnails.DATA:视频缩略图的文件路径
        String[] thumbColumns = { MediaStore.Video.Thumbnails.DATA,
                MediaStore.Video.Thumbnails.VIDEO_ID };

        // MediaStore.Video.Media.DATA：视频文件路径；
        // MediaStore.Video.Media.DISPLAY_NAME : 视频文件名，如 testVideo.mp4
        // MediaStore.Video.Media.TITLE: 视频标题 : testVideo
        String[] mediaColumns = { MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA, MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.DISPLAY_NAME };

        Cursor cursor = getActivity().getContentResolver().query(uri,
                mediaColumns, null, null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);

        if(cursor != null){
            if (cursor.moveToFirst()) {
                do {
                    MediaBean media = new MediaBean();
                    int id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.Video.Media._ID));
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                    String thumbPath = "";
                    Cursor thumbCursor = getActivity().getContentResolver().query(
                            MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                            thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID
                                    + "=" + id, null, null);
                    if (thumbCursor.moveToFirst()) {
                        thumbPath = (thumbCursor.getString(thumbCursor
                                .getColumnIndex(MediaStore.Video.Thumbnails.DATA)));
                    }
                    media.setTitle(title);
                    media.setPath(path);
                    media.setThumb(thumbPath);
                    mediaList.add(media);
                } while (cursor.moveToNext());
            }
        }
    }

    private void refreshView(){
        if(mediaList.isEmpty()){
            mTextView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else{
            mTextView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mAdapter.setDataList(mediaList);
            mAdapter.notifyDataSetChanged();
        }

    }
}
