package com.balius.filimo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.balius.filimo.R;
import com.balius.filimo.activities.VideoPlayerActivity;
import com.balius.filimo.model.lastesvideo.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PageAdapter extends PagerAdapter {

    List<Video> videoList;
    Context context;
    LayoutInflater inflater;

    public PageAdapter(Context context, List<Video> videoList) {
        this.videoList= videoList;
        this.context= context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        View view = inflater.inflate(R.layout.pager_row,null);

        AppCompatImageView img_pager = view.findViewById(R.id.img_pager);

        container.addView(view, 0);

        Video video = videoList.get(position);

        Picasso.get().load(video.getVideoThumbnailB())
               .into(img_pager);

        img_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("video",video);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


        return view;
    }
}
