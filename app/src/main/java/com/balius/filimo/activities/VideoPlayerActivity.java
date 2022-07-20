package com.balius.filimo.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balius.filimo.R;
import com.balius.filimo.adapter.CommentAdapter;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.ActivityVideoPlayerBinding;
import com.balius.filimo.model.Save;
import com.balius.filimo.model.lastesvideo.Video;
import com.balius.filimo.model.singelvideo.comment.SingleVideo;
import com.balius.filimo.model.singelvideo.comment.SingleVideoModel;
import com.balius.filimo.model.singelvideo.comment.UserComment;
import com.balius.filimo.model.singelvideo.nocomment.CVideo;
import com.balius.filimo.model.singelvideo.nocomment.CVideoModel;
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
    CVideo cVideo;
    Video video;
    List<UserComment> userCommentList;
    //Save save;
    String id;

//    String video_id;
//    String video_title;
//    String video_duration;
//    String video_description;
//    String video_image;
//    String video_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        webserviceCaller = new WebserviceCaller();
        bundle = getIntent().getExtras();

        video = bundle.getParcelable("video");

        db = Db.getInstance(getApplicationContext());
        id = video.getId();


        int vid = Integer.parseInt(id);

        Log.e("", "");

        webserviceCaller.getSingleVideo(vid, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                SingleVideoModel singleVideoModel;
                singleVideoModel = (SingleVideoModel) responseMessage;

                List<SingleVideo> singleVideoList = singleVideoModel.getAllInOneVideo();
                singleVideo = singleVideoList.get(0);
                userCommentList = singleVideo.getUserComments();


                binding.recycleComments.setAdapter(new CommentAdapter(getApplicationContext(), userCommentList));
                binding.recycleComments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL, false));

                Log.e("", "succccesssss");
            }

            @Override
            public void onFailure(String onErrorMessage) {

                Log.e("failllll", "" + onErrorMessage);

                //if video dosent have comment
                webserviceCaller.getCmSingleVideo(vid, new IResponseListener() {
                    @Override
                    public void onSuccess(Object responseMessage) {
                        Log.e("failll &succusse", "");

                        CVideoModel cVideoModel;
                        cVideoModel = (CVideoModel) responseMessage;

                        List<CVideo> cVideoList = cVideoModel.getAllInOneVideo();
                        cVideo = cVideoList.get(0);

                    }

                    @Override
                    public void onFailure(String onErrorMessage) {

                        Log.e("Failll & Failll", "");
                    }
                });

            }
        });

        Picasso.get().load(video.getVideoThumbnailB()).into(binding.imgCover);
        binding.txtTitle.setText(video.getVideoTitle());
        binding.txtTime.setText(video.getVideoDuration() + " دقیقه ");

        Spanned spanned = Html.fromHtml(video.getVideoDescription());
        binding.txtDescription.setText(spanned);

        binding.lblName.setText(video.getVideoTitle());

        player = new ExoPlayer.Builder(this).build();
        MediaItem item = MediaItem.fromUri(Uri.parse(video.getVideoUrl()));
        player.setMediaItem(item);
        binding.videoView.setPlayer(player);
        player.play();



        List<Save> saveList = db.iDao().getSaveVideos(video.getVideoTitle());

        if (saveList.size() > 0) {

            binding.imgSave.setBackgroundResource(R.drawable.icon_save_dark);

        } else {
            binding.imgSave.setBackgroundResource(R.drawable.icon_save);
        }



        binding.imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Save> saveList = db.iDao().getSaveVideos(video.getVideoTitle());

                if (saveList.size() > 0) {
                    binding.imgSave.setBackgroundResource(R.drawable.icon_save);
                    db.iDao().deleteSave(video.getId());
                } else {
                    binding.imgSave.setBackgroundResource(R.drawable.icon_save_dark);
                    video.setSave("1");
                    Save save  = new Save(video.getId(),video.getVideoTitle(),video.getVideoThumbnailB(),
                            video.getVideoUrl(),video.getVideoDescription(),video.getVideoDuration());
                    long result = db.iDao().addSave(save);
                }
            }

        });

        binding.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage = "Share the video";
                shareMessage = shareMessage + video.getVideoUrl();
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.stop();
        }
    }
}