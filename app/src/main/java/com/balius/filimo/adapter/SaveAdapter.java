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
import com.balius.filimo.model.Save;
import com.balius.filimo.model.lastesvideo.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.SaveVH> {
    List<Save> saveList;
    Context context;

    Video video;

    public SaveAdapter(Context context, List<Save> saveList) {
        this.context = context;
        this.saveList = saveList;

        video = new Video();
    }

    @NonNull
    @Override
    public SaveVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.save_row, null);

        return new SaveVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveVH holder, int position) {
        Save save = saveList.get(position);

        holder.txt_video_name.setText(save.getVideo_title());

        Picasso.get().load(save.getVideo_image()).into(holder.img_video);

        holder.rel_save_video.setOnClickListener(view -> {
            video.setId(save.getVideo_id());
            video.setVideoTitle(save.getVideo_title());
            video.setVideoThumbnailB(save.getVideo_image());
            video.setVideoUrl(save.getVideo_url());
            video.setVideoDescription(save.getVideo_description());
            video.setVideoDuration(save.getVideo_time());

            Intent intent = new Intent(context,VideoPlayerActivity.class);
            intent.putExtra("video",video);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return saveList.size();
    }

    static class SaveVH extends RecyclerView.ViewHolder {
        AppCompatImageView img_video;
        AppCompatTextView txt_video_name;
        RelativeLayout rel_save_video;

        public SaveVH(@NonNull View itemView) {
            super(itemView);
            img_video = itemView.findViewById(R.id.img_video);
            txt_video_name = itemView.findViewById(R.id.txt_video_name);
            rel_save_video = itemView.findViewById(R.id.rel_save_video);
        }
    }

}
