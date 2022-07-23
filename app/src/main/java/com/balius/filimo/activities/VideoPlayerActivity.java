package com.balius.filimo.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balius.filimo.R;
import com.balius.filimo.adapter.CommentAdapter;
import com.balius.filimo.adapter.RelatedAdapter;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.ActivityVideoPlayerBinding;
import com.balius.filimo.model.LikedVideos;
import com.balius.filimo.model.Save;
import com.balius.filimo.model.Watched;
import com.balius.filimo.model.lastesvideo.Video;
import com.balius.filimo.model.login.Login;
import com.balius.filimo.model.singelvideo.comment.Related;
import com.balius.filimo.model.singelvideo.comment.SingleVideo;
import com.balius.filimo.model.singelvideo.comment.SingleVideoModel;
import com.balius.filimo.model.singelvideo.comment.UserComment;
import com.balius.filimo.model.singelvideo.nocomment.CVideo;
import com.balius.filimo.model.singelvideo.nocomment.CVideoModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.BlurTransformation;

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
    String id;

    List<Related> relatedList;


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
        binding.cardNoComment.setVisibility(View.INVISIBLE);


        webserviceCaller.getSingleVideo(vid, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                SingleVideoModel singleVideoModel;
                singleVideoModel = (SingleVideoModel) responseMessage;

                List<SingleVideo> singleVideoList = singleVideoModel.getAllInOneVideo();
                singleVideo = singleVideoList.get(0);
                userCommentList = singleVideo.getUserComments();

                relatedList = singleVideo.getRelated();

                binding.recycleComments.setAdapter(new CommentAdapter(getApplicationContext(), userCommentList));
                binding.recycleComments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL, false));

                binding.recycleRelated.setAdapter(new RelatedAdapter(relatedList, getApplicationContext()));
                binding.recycleRelated.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.HORIZONTAL, false));
                Log.e("", "succccesssss");
            }

            @Override
            public void onFailure(String onErrorMessage) {
                binding.recycleComments.setVisibility(View.INVISIBLE);
                binding.cardNoComment.setVisibility(View.VISIBLE);

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

                        relatedList = cVideo.getRelated();

                        binding.recycleRelated.setAdapter(new RelatedAdapter(relatedList, getApplicationContext()));
                        binding.recycleRelated.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                                RecyclerView.HORIZONTAL, false));

                    }

                    @Override
                    public void onFailure(String onErrorMessage) {

                        Log.e("Failll & Failll", "");
                    }
                });

            }
        });

        Picasso.get().load(video.getVideoThumbnailB()).into(binding.imgCover);
        Picasso.get().load(video.getVideoThumbnailS())
                .transform(new BlurTransformation(getApplicationContext(), 15, 1))
                .into(binding.imgCoverMat);



        binding.txtTitle.setText(video.getVideoTitle());
        binding.txtTime.setText(video.getVideoDuration() + " دقیقه ");

        Spanned spanned = Html.fromHtml(video.getVideoDescription());
        binding.txtDescription.setText(spanned);

        binding.lblName.setText(video.getVideoTitle());

        binding.videoView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
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

        List<LikedVideos> likedVideosList = db.iDao().getLikeVideos(video.getVideoTitle());

        if (likedVideosList.size() > 0) {

            LikedVideos likedVideos = likedVideosList.get(0);

            if (likedVideos.isLike_state()) {
                binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.green), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);

            } else {
                binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
            }

        }
        else {
            binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                    R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
            binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                    R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
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
                    Save save = new Save(video.getId(), video.getVideoTitle(), video.getVideoThumbnailB(),
                            video.getVideoUrl(), video.getVideoDescription(), video.getVideoDuration());
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


        List<Watched> watchedList = db.iDao().getWatchedVideos(video.getVideoTitle());
        if (watchedList.size() > 0) {
            binding.btnWatch.setText(R.string.watch);
        } else {
            binding.btnWatch.setText(R.string.enter_and_watch);

        }
        binding.btnWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra("url", video.getVideoUrl());
                intent.putExtra("title", video.getVideoTitle());
                startActivity(intent);

                List<Watched> watchedList = db.iDao().getWatchedVideos(video.getVideoTitle());

                if (watchedList.size() <= 0) {
                    Watched watched = new Watched(video.getId(), video.getVideoThumbnailB(), video.getVideoTitle()
                            , video.getVideoUrl(), video.getVideoDescription(), video.getVideoDuration());

                    db.iDao().addWatch(watched);
                }

            }
        });

        binding.imgSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Login> loginList = db.iDao().getAllAccount();
                String text = binding.edtComment.getText().toString();
                if (loginList.size() > 0) {

                    if (text.isEmpty()) {
                        Toast.makeText(VideoPlayerActivity.this, R.string.pls_enter_comment, Toast.LENGTH_SHORT).show();
                    } else {
                        Login login = loginList.get(0);

                        String username = login.getName();
                        webserviceCaller.insertComment(text, username, vid, new IResponseListener() {
                            @Override
                            public void onSuccess(Object responseMessage) {
                                Toast.makeText(VideoPlayerActivity.this, R.string.succefully_add_comment, Toast.LENGTH_SHORT).show();
                                binding.edtComment.setText(null);
                                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                            }

                            @Override
                            public void onFailure(String onErrorMessage) {
                                Toast.makeText(VideoPlayerActivity.this, R.string.error_insert_comment, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                } else {
                    Toast.makeText(VideoPlayerActivity.this, R.string.login_firs, Toast.LENGTH_SHORT).show();
                }


            }
        });


        binding.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<LikedVideos> likedVideosList = db.iDao().getLikeVideos(video.getVideoTitle());

                if (likedVideosList.size() > 0) {
                    LikedVideos likedVideos = likedVideosList.get(0);

                    if (likedVideos.isLike_state()) {
                        binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                                R.color.green), android.graphics.PorterDuff.Mode.SRC_IN);
                        binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                                R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                    } else {
                        binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                                R.color.green), android.graphics.PorterDuff.Mode.SRC_IN);
                        binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                                R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                        boolean like  =true;
                        boolean dislike = false;
                        db.iDao().updateLike(like,video.getVideoTitle());
                        db.iDao().updateDisLike(dislike,video.getVideoTitle());
                    }
                }
                else {
                    binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.green), android.graphics.PorterDuff.Mode.SRC_IN);
                    binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                    LikedVideos likedVideos = new LikedVideos(video.getVideoTitle(),true,false);
                    db.iDao().addLike(likedVideos);
                }


            }
        });

        binding.imgDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<LikedVideos> likedVideosList = db.iDao().getLikeVideos(video.getVideoTitle());

                if (likedVideosList.size() > 0) {
                    LikedVideos likedVideos = likedVideosList.get(0);

                    if (likedVideos.isLike_state()) {
                        binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                                R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                        binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                                R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
                    } else {
                        binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                                R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                        binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                                R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
                        boolean like  =false;
                        boolean dislike = true;
                        db.iDao().updateLike(like,video.getVideoTitle());
                        db.iDao().updateDisLike(dislike,video.getVideoTitle());
                    }

                }
                else {
                    binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.black), android.graphics.PorterDuff.Mode.SRC_IN);
                    binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
                    LikedVideos likedVideos = new LikedVideos(video.getVideoTitle(),false,true);
                    db.iDao().addLike(likedVideos);
                }


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