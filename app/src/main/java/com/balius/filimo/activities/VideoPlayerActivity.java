package com.balius.filimo.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.ActivityVideoPlayerBinding;
import com.balius.filimo.model.lastesvideo.Video;
import com.balius.filimo.model.singelvideo.SingleVideo;
import com.balius.filimo.model.singelvideo.SingleVideoModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoPlayerActivity extends AppCompatActivity {
    ActivityVideoPlayerBinding binding;
    Bundle bundle;
    ExoPlayer player;
    Db db;
    WebserviceCaller webserviceCaller;
    SingleVideo singleVideo;
    Video video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        webserviceCaller = new WebserviceCaller();
        bundle = getIntent().getExtras();
        video = bundle.getParcelable("video");

        Log.e("", "");

        binding.txtTitle.setTypeface(null, Typeface.BOLD);
        db = Db.getInstance(getApplicationContext());

        String id = video.getId();

        int vid =Integer.parseInt(id);

        Log.e("", "");

        webserviceCaller.getSingleVideo(vid, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                SingleVideoModel singleVideoModel = new SingleVideoModel();
                singleVideoModel = (SingleVideoModel) responseMessage;

                List<SingleVideo> singleVideoList = singleVideoModel.getAllInOneVideo();
                singleVideo = singleVideoList.get(0);

                Log.e("","succccesssss");
            }

            @Override
            public void onFailure(String onErrorMessage) {
               Log.e("failllll",""+onErrorMessage);
            }
        });
//
//        Picasso.get().load(singleVideo.getVideoThumbnailB()).into(binding.imgCover);
//        binding.txtTitle.setText(singleVideo.getVideoTitle().toString());
//        binding.txtTime.setText(singleVideo.getVideoDuration() + " دقیقه ");
//
//        Spanned spanned = Html.fromHtml(singleVideo.getVideoDescription());
//        binding.txtDescription.setText(spanned);
//
//        binding.lblName.setText(singleVideo.getVideoTitle());
//
//        List<Video> videoList = db.iDao().searchVideo(singleVideo.getVideoTitle());
//

//        if (videoList.size() > 0) {
//            video = videoList.get(0);
//            if (video.getSave().equals("1")){
//                binding.imgSave.setBackgroundResource(R.drawable.icon_save_dark);
//            }
//            else {
//                binding.imgSave.setBackgroundResource(R.drawable.icon_save);
//            }
//
//        } else {
//            binding.imgSave.setBackgroundResource(R.drawable.icon_save);
//        }


//        player = new ExoPlayer.Builder(this).build();
//        MediaItem item = MediaItem.fromUri(Uri.parse(singleVideo.getVideoUrl()));
//        player.setMediaItem(item);
//        binding.videoView.setPlayer(player);
//        player.play();
//

//        binding.imgSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                if (videoList.size() > 0) {
//
//
//                    if (video.getSave().equals("1")) {
//                        binding.imgSave.setBackgroundResource(R.drawable.icon_save);
//                        db.iDao().updateSave("0");}
//
//                    if (video.getSave().equals("0")){
//                        binding.imgSave.setBackgroundResource(R.drawable.icon_save_dark);
//                        db.iDao().updateSave("1");
//                    }
//
//                }
//                else {
//                    video.setSave("1");
//                    long result = db.iDao().addVideo(video);
//                }}
//
//        });

        //watch history
//        binding.videoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

//        binding.imgShare.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent shareIntent = new Intent(Intent.ACTION_SEND);
//                shareIntent.setType("text/plain");
//                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
//                String shareMessage = "Share the video";
//                shareMessage = shareMessage + singleVideo.getVideoUrl();
//                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
//                startActivity(Intent.createChooser(shareIntent, "choose one"));
//            }
//        });
//

//        binding.imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.stop();
        }
    }
}