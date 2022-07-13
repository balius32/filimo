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

public class HorizontalVideoAdapter extends RecyclerView.Adapter<HorizontalVideoAdapter.horzonVideoVh> {

    Context context;
    List<Video> videoList;
    LayoutInflater inflater;
    Db db;

    public HorizontalVideoAdapter(Context context, List<Video> videoList) {
        this.context = context;
        this.videoList = videoList;
        inflater = LayoutInflater.from(context);
        db = Db.getInstance(context);
    }


    @NonNull
    @Override
    public horzonVideoVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.horizon_video_row,null);

        return new horzonVideoVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull horzonVideoVh holder, int position) {
        Video video = videoList.get(position);

        holder.txt_video_name.setText(video.getVideoTitle());
        Picasso.get().load(video.getVideoThumbnailB()).into(holder.img_video);


        holder.rel_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (db.iDao().searchVideo(video.getVideoTitle()).size() > 0) {


                } else {
                    video.setSave("0");
                    video.setLike("0");
                    video.setDislike("0");
                    video.setWatched("0");
                }
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

    class horzonVideoVh extends RecyclerView.ViewHolder{
        AppCompatImageView img_video;
        AppCompatTextView txt_video_name;
        RelativeLayout rel_video;
        public horzonVideoVh(@NonNull View itemView) {
            super(itemView);

            img_video = itemView.findViewById(R.id.img_video);
            txt_video_name = itemView.findViewById(R.id.txt_video_name);
            rel_video = itemView.findViewById(R.id.rel_video);
        }
    }

}
