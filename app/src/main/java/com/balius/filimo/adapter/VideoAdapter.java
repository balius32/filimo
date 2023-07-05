package com.balius.filimo.adapter;

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
import com.balius.filimo.database.Db;
import com.balius.filimo.model.lastesvideo.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoVH> {
    Context context;
    List<Video> videoList;
    Db db;

    public VideoAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
        db = Db.getInstance(context);
    }

    @NonNull
    @Override
    public VideoVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.video_row, null);

        return new VideoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoVH holder, int position) {
        Video video = videoList.get(position);

        holder.txt_video_name.setText(video.getVideoTitle());
        Picasso.get().load(video.getVideoThumbnailB()).into(holder.img_video);


        holder.rel_video.setOnClickListener(view -> {
            Intent intent = new Intent(context, VideoPlayerActivity.class);

            intent.putExtra("video", video);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    static class VideoVH extends RecyclerView.ViewHolder {
        AppCompatImageView img_video;
        AppCompatTextView txt_video_name;
        RelativeLayout rel_video;

        public VideoVH(@NonNull View itemView) {
            super(itemView);

            img_video = itemView.findViewById(R.id.img_video);
            txt_video_name = itemView.findViewById(R.id.txt_video_name);
            rel_video = itemView.findViewById(R.id.rel_video);

        }
    }
}
