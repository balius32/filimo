package com.balius.filimo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;

import com.balius.filimo.R;
import com.balius.filimo.activities.VideoPlayerActivity;
import com.balius.filimo.model.lastesvideo.Video;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.picasso.transformations.BlurTransformation;

public class SliderPagerAdapter extends
        SliderViewAdapter<SliderPagerAdapter.SliderAdapterVH> {

    Context context;
    List<Video> mSliderItems = new ArrayList<>();

    public SliderPagerAdapter(Context context, List<Video> mSliderItems) {
        this.context = context;
        this.mSliderItems= mSliderItems;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_row, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {


        Video video = mSliderItems.get(position);

        Picasso.get().load(video.getVideoThumbnailB())
                .into(viewHolder.img_pager);

        Picasso.get().load(video.getVideoThumbnailS())
                .transform(new BlurTransformation(context, 15, 1))
                .into(viewHolder.img_pager_blur);


        viewHolder.img_pager.setOnClickListener(new View.OnClickListener() {
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
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        AppCompatImageView img_pager;
        AppCompatImageView img_pager_blur;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            img_pager = itemView.findViewById(R.id.img_pager);
            img_pager_blur = itemView.findViewById(R.id.img_pager_blur);
        }
    }

}
