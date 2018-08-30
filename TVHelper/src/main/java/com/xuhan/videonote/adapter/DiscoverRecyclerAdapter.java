package com.xuhan.videonote.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xuhan.videonote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  xuhan on 18-3-3.
 */

public class DiscoverRecyclerAdapter extends RecyclerView.Adapter<DiscoverRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mDataList = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_icon);
            itemName = itemView.findViewById(R.id.item_text);
        }
    }

    public DiscoverRecyclerAdapter() {
    }

    public DiscoverRecyclerAdapter(List<String> dataList) {
        this.mDataList = dataList;
    }

    public void setDataList(List<String> dataList) {
        this.mDataList = dataList;
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_discover, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.itemName.setText(mDataList.get(position));
        Glide.with(mContext).load(R.drawable.icon_movie).into(holder.itemImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.isEmpty() ? 0 : mDataList.size();
    }

    public interface OnItemClickListener {
        /**
         * item点击事件
         *
         * @param position
         */
        void onItemClick(int position);
    }
}
