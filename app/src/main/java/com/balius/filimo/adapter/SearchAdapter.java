package com.balius.filimo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.balius.filimo.R;
import com.balius.filimo.activities.VideoPlayerActivity;
import com.balius.filimo.model.lastesvideo.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchVh> {
    Context context;
    List<Video> videoList;
    LayoutInflater inflater;

    public SearchAdapter( Context context, List<Video> videoList) {
        this.context=context;
        this.videoList=videoList;
        inflater= LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SearchVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_row,null);
        return new SearchVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchVh holder, int position) {
        Video video = videoList.get(position);

        holder.txt_name.setText(video.getVideoTitle());
        Picasso.get().load(video.getVideoThumbnailB()).into(holder.img_video);

        holder.const_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("video", video);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    class SearchVh extends RecyclerView.ViewHolder{
        AppCompatImageView img_video;
        AppCompatTextView txt_name;
        ConstraintLayout const_layout;

        public SearchVh(@NonNull View itemView) {
            super(itemView);
            img_video = itemView.findViewById(R.id.img_video);
            txt_name = itemView.findViewById(R.id.txt_name);
           const_layout = itemView.findViewById(R.id.const_layout);
        }
    }
}
