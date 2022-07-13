package com.balius.filimo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.PagerAdapter;

import com.balius.filimo.R;
import com.balius.filimo.model.lastesvideo.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PageAdapter extends PagerAdapter {

    List<Video> videoList;
    Context context;

    public PageAdapter(Context context, List<Video> videoList) {
        this.videoList= videoList;
        this.context= context;
    }

    @Override
    public int getCount() {
        return videoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.pager_row,null);

        AppCompatImageView img_pager = view.findViewById(R.id.img_pager);

        Video video = videoList.get(position);

        Picasso.get().load(video.getVideoThumbnailB())
                .placeholder(R.drawable.raymon)
                .error(R.drawable.raymon).into(img_pager);


        return view;
    }
}
