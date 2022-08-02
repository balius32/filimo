package com.balius.filimo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.balius.filimo.R;
import com.balius.filimo.activities.VideoPlayerActivity;
import com.balius.filimo.model.lastesvideo.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoCategoryHAdapter extends RecyclerView.Adapter<VideoCategoryHAdapter.CatHVH> {

    List<Video> videoList;
    Context context;
    LayoutInflater inflater;

    public VideoCategoryHAdapter(List<Video> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CatHVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.category_horizontal_row,null);
        return new CatHVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatHVH holder, int position) {
        Video video = videoList.get(position);

        holder.txt_video_name.setText(video.getVideoTitle());
        holder.txt_category_name.setText(video.getCategoryName());
        Picasso.get().load(video.getVideoThumbnailB()).into(holder.img_video);
        holder.card_category.setOnClickListener(view -> {
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

    class CatHVH extends RecyclerView.ViewHolder{
        AppCompatImageView img_video;
        AppCompatTextView txt_video_name;
        AppCompatTextView txt_category_name;
        CardView card_category;

        public CatHVH(@NonNull View itemView) {
            super(itemView);
            img_video= itemView.findViewById(R.id.img_video);
            txt_video_name= itemView.findViewById(R.id.txt_video_name);
            txt_category_name= itemView.findViewById(R.id.txt_category_name);
            card_category= itemView.findViewById(R.id.card_category);
        }
    }
}
