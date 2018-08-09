package com.xuhan.videonote.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xuhan.videonote.R;
import com.xuhan.videonote.entity.MovieEntity;

import java.util.List;

/**
 * @author xuhan
 */

public class DiscreteScrollViewAdapter extends RecyclerView.Adapter<DiscreteScrollViewAdapter.ViewHolder> {

    private List<MovieEntity.SubjectsEntity> data;
    private OnItemClickListener mListener;

    public DiscreteScrollViewAdapter(List<MovieEntity.SubjectsEntity> data) {
        this.data = data;
    }

    public void setData(List<MovieEntity.SubjectsEntity> data) {
        this.data = data;
    }

    public void setClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_scrollview_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MovieEntity.SubjectsEntity subjects = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(subjects.getImages().getLarge())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
