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
import com.balius.filimo.model.Save;
import com.balius.filimo.model.Watched;
import com.balius.filimo.model.lastesvideo.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WatchedAdapter extends RecyclerView.Adapter<WatchedAdapter.watchVH> {

    List<Watched> watchedList;
    Context context;

    public WatchedAdapter( List<Watched> watchedList, Context context) {
        this.watchedList = watchedList;
        this.context = context;

    }

    @NonNull
    @Override
    public watchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.save_row,null);
        return new watchVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull watchVH holder, int position) {
      Watched watched = watchedList.get(position);

        Video video = new Video();

        holder.txt_video_name.setText(watched.getVideo_title());

        Picasso.get().load(watched.getVideo_image()).into(holder.img_video);

        holder.rel_save_video.setOnClickListener(view -> {
            video.setId(watched.getVideo_id());
            video.setVideoTitle(watched.getVideo_title());
            video.setVideoThumbnailB(watched.getVideo_image());
            video.setVideoUrl(watched.getVideo_url());
            video.setVideoDescription(watched.getVideo_description());
            video.setVideoDuration(watched.getVideo_time());

            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra("video",video);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return watchedList.size();
    }

    static class watchVH extends RecyclerView.ViewHolder{

        AppCompatImageView img_video;
        AppCompatTextView txt_video_name;
        RelativeLayout rel_save_video;

        public watchVH(@NonNull View itemView) {
            super(itemView);

            img_video = itemView.findViewById(R.id.img_video);
            txt_video_name = itemView.findViewById(R.id.txt_video_name);
            rel_save_video = itemView.findViewById(R.id.rel_save_video);
        }
    }
}
