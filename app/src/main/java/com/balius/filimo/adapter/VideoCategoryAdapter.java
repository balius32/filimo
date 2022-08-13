package com.balius.filimo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.balius.filimo.R;
import com.balius.filimo.activities.VideoPlayerActivity;
import com.balius.filimo.model.lastesvideo.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoCategoryAdapter extends RecyclerView.Adapter<VideoCategoryAdapter.CatVH> {
    List<Video> videoList;
    Context context;


    public VideoCategoryAdapter(List<Video> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;

    }

    @NonNull
    @Override
    public CatVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams")
        View view = inflater.inflate(R.layout.save_row,null);

        return new CatVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatVH holder, int position) {

        Video video = videoList.get(position);

        holder.txt_video_name.setText(video.getVideoTitle());

        Picasso.get().load(video.getVideoThumbnailB()).into(holder.img_video);

        holder.rel_save_video.setOnClickListener(view -> {
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra("video",video);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    static class CatVH extends RecyclerView.ViewHolder{
        AppCompatImageView img_video;
        AppCompatTextView txt_video_name;
        RelativeLayout rel_save_video;
        public CatVH(@NonNull View itemView) {
            super(itemView);
            img_video = itemView.findViewById(R.id.img_video);
            txt_video_name = itemView.findViewById(R.id.txt_video_name);
            rel_save_video = itemView.findViewById(R.id.rel_save_video);
        }
    }
}
