package com.xuhan.videonote.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuhan.videonote.R;
import com.xuhan.videonote.bean.LocalMediaEntity;

import java.util.List;

/**
 * Created by xuhan on 18-4-27.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<LocalMediaEntity> mDataList;
    private OnItemClickListener mItemClickListener;

    public VideoListAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public VideoListAdapter(Context context, List<LocalMediaEntity> dataList) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mDataList = dataList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setDataList(List<LocalMediaEntity> dataList) {
        this.mDataList = dataList;
    }

    @Override
    public VideoListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.list_recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final VideoListAdapter.MyViewHolder holder, final int position) {
        LocalMediaEntity media = mDataList.get(position);
        holder.mTextView.setText(media.getTitle());
        Glide.with(mContext).load(media.getThumb()).into(holder.mImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image);
            mTextView = (TextView) itemView.findViewById(R.id.item_title);
        }
    }
}
