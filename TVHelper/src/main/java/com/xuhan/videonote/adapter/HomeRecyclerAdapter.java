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

import com.bumptech.glide.Glide;
import com.xuhan.videonote.R;
import com.xuhan.videonote.bean.VideoBean;

import java.util.List;
import java.util.Random;

/**
 * Created by xuhan on 18-3-3.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<VideoBean> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView cardImage;
        TextView cardName;
        TextView cardNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            cardImage = itemView.findViewById(R.id.card_image);
            cardName = itemView.findViewById(R.id.card_name);
            cardNumber = itemView.findViewById(R.id.card_sub);
        }
    }

    public HomeRecyclerAdapter() {
    }

    public HomeRecyclerAdapter(List<VideoBean> dataList) {
        this.mDataList = dataList;
    }

    public void setDataList(List<VideoBean> dataList) {
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoBean data = mDataList.get(position);
        holder.cardName.setText(data.getName());
        holder.cardNumber.setText(data.getEpisodes());
        int[] images = {R.drawable.image01,R.drawable.image02,R.drawable.image03,R.drawable.image04};
        int index = new Random().nextInt(3);
        Glide.with(mContext).load(images[index]).into(holder.cardImage);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
