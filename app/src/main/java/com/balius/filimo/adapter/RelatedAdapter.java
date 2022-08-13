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
import com.balius.filimo.model.lastesvideo.Video;
import com.balius.filimo.model.singelvideo.comment.Related;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RelatedAdapter extends RecyclerView.Adapter<RelatedAdapter.RelatedVH> {
    List<Related> relatedList ;
    Context context;


    public RelatedAdapter(List<Related> relatedList, Context context) {
        this.relatedList = relatedList;
        this.context = context;
    }

    @NonNull
    @Override
    public RelatedVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.video_row,null);
        return new RelatedVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedVH holder, int position) {

        Related related = relatedList.get(position);
        Video video = new Video();

        holder.txt_video_name.setText(related.getVideoTitle());
        Picasso.get().load(related.getVideoThumbnailB()).into(holder.img_video);


        holder.rel_video.setOnClickListener(view -> {
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            video.setId(related.getId());
            video.setVideoTitle(related.getVideoTitle());
            video.setVideoThumbnailB(related.getVideoThumbnailB());
            video.setVideoUrl(related.getVideoUrl());
            video.setVideoDescription(related.getVideoDescription());
            video.setVideoDuration(related.getVideoDuration());

            intent.putExtra("video", video);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return relatedList.size();
    }

    static class RelatedVH extends RecyclerView.ViewHolder{

        AppCompatImageView img_video;
        AppCompatTextView txt_video_name;
        RelativeLayout rel_video;

        public RelatedVH(@NonNull View itemView) {
            super(itemView);

            img_video = itemView.findViewById(R.id.img_video);
            txt_video_name = itemView.findViewById(R.id.txt_video_name);
            rel_video = itemView.findViewById(R.id.rel_video);

        }
    }
}
