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

public class MovieSubjectAdapter extends RecyclerView.Adapter<MovieSubjectAdapter.ViewHolder> {

    private RecyclerView parentRecycler;
    private List<MovieEntity.SubjectsEntity> data;

    public MovieSubjectAdapter(List<MovieEntity.SubjectsEntity> data) {
        this.data = data;
    }

    public void setData(List<MovieEntity.SubjectsEntity> data) {
        this.data = data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        parentRecycler = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_movie_subject_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieEntity.SubjectsEntity subject = data.get(position);
        Glide.with(holder.itemView.getContext())
                .load(subject.getImages().getSmall())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movie_image);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            parentRecycler.smoothScrollToPosition(getAdapterPosition());
        }
    }
}
