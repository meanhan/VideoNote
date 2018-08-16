package com.xuhan.videonote.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.BoxingManager;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bumptech.glide.Glide;
import com.xuhan.videonote.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuhan on 18-3-3.
 */

public class MomentRecyclerAdapter extends RecyclerView.Adapter<MomentRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<BaseMedia> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;

        public ViewHolder(View itemView) {
            super(itemView);
            cardImage = itemView.findViewById(R.id.image);
        }
    }

    public MomentRecyclerAdapter() {
        mDataList = new ArrayList<>();
    }

    public void setDataList(List<BaseMedia> dataList) {
        if (dataList == null) {
            return;
        }
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_moment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.cardImage.setImageResource(BoxingManager.getInstance().getBoxingConfig().getMediaPlaceHolderRes());
        BaseMedia media = mDataList.get(position);
        String path;
        if (media instanceof ImageMedia) {
            path = ((ImageMedia) media).getThumbnailPath();
        } else {
            path = media.getPath();
        }
//        Glide.with(mContext).load(path).into(holder.cardImage);
        BoxingMediaLoader.getInstance().displayThumbnail(holder.cardImage, path, 400, 500);
    }

    @Override
    public int getItemCount() {
        return mDataList.isEmpty() ? 0 : mDataList.size();
    }


}
