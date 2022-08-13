package com.balius.filimo.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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
import com.google.android.material.appbar.AppBarLayout;
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
    int vid;
    List<Related> relatedList;

    @SuppressLint("SetTextI18n")
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


        vid = Integer.parseInt(id);
        binding.cardNoComment.setVisibility(View.INVISIBLE);


        //this for related and comments
        webserviceCaller.getSingleVideo(vid, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                if (responseMessage != null) {


                    commentVideo(responseMessage);
                    Log.e("", "succccesssss");
                } else {
                    binding.appBarLayout.setVisibility(View.GONE);
                    binding.scrollView.setVisibility(View.GONE);
                    binding.constraintNoSignal.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String onErrorMessage) {
                Log.e("failllll", "" + onErrorMessage);

                //if video dosent have comment
                noCommentVideo();
            }
        });

        Picasso.get().load(video.getVideoThumbnailB()).into(binding.imgCover);
        Picasso.get().load(video.getVideoThumbnailB())
                .transform(new BlurTransformation(getApplicationContext(), 50, 1))
                .into(binding.imgCoverMat);

        binding.txtTitle.setText(video.getVideoTitle());
        binding.txtTime.setText(video.getVideoDuration() + " دقیقه ");

        Spanned spanned = Html.fromHtml(video.getVideoDescription());
        binding.txtDescription.setText(spanned);

        binding.lblNameWhite.setText(video.getVideoTitle());
        binding.lblNameBlack.setText(video.getVideoTitle());

        binding.videoView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        player = new ExoPlayer.Builder(this).build();
        MediaItem item = MediaItem.fromUri(Uri.parse(video.getVideoUrl()));
        player.setMediaItem(item);
        binding.videoView.setPlayer(player);

        Picasso.get().load(video.getVideoThumbnailB()).into(binding.imgVideoPlayer);

        binding.relPlayer.setOnClickListener(view -> {
            binding.relImgPlayer.setVisibility(View.INVISIBLE);
            binding.videoView.setVisibility(View.VISIBLE);
            player.prepare();
            player.play();
        });

        checkSaveWhite();

        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    //collapse
                    //black
                    binding.imgSaveWhite.setVisibility(View.GONE);
                    binding.imgSaveBlack.setVisibility(View.VISIBLE);
                    binding.imgShareWhite.setVisibility(View.GONE);
                    binding.imgShareBlack.setVisibility(View.VISIBLE);

                    binding.lblNameWhite.setVisibility(View.GONE);
                    binding.lblNameBlack.setVisibility(View.VISIBLE);

                    binding.imgBackBlack.setVisibility(View.VISIBLE);
                    binding.imgBackWhite.setVisibility(View.GONE);

                    isShow = true;

                } else if (isShow) {
                    //expanded
                    //white

                    binding.imgSaveBlack.setVisibility(View.GONE);
                    binding.imgSaveWhite.setVisibility(View.VISIBLE);
                    binding.imgShareWhite.setVisibility(View.VISIBLE);
                    binding.imgShareBlack.setVisibility(View.GONE);

                    binding.lblNameWhite.setVisibility(View.VISIBLE);
                    binding.lblNameBlack.setVisibility(View.GONE);

                    binding.imgBackBlack.setVisibility(View.GONE);
                    binding.imgBackWhite.setVisibility(View.VISIBLE);

                    isShow = false;
                }

            }
        });

        binding.imgSaveWhite.setOnClickListener(view -> {

            List<Save> saveList1 = db.iDao().getSaveVideos(video.getVideoTitle());

            if (saveList1.size() > 0) {
                binding.imgSaveWhite.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                binding.imgSaveBlack.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                db.iDao().deleteSave(video.getId());
            } else {
                binding.imgSaveWhite.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
                binding.imgSaveBlack.setBackgroundResource(R.drawable.book_dark);


                Save save = new Save(video.getId(), video.getVideoTitle(), video.getVideoThumbnailB(),
                        video.getVideoUrl(), video.getVideoDescription(), video.getVideoDuration());
                db.iDao().addSave(save);
            }
        });

        binding.imgSaveBlack.setOnClickListener(view -> {

            List<Save> saveList1 = db.iDao().getSaveVideos(video.getVideoTitle());

            if (saveList1.size() > 0) {
                binding.imgSaveWhite.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                binding.imgSaveBlack.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);
                db.iDao().deleteSave(video.getId());
            } else {
                binding.imgSaveWhite.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
                binding.imgSaveBlack.setBackgroundResource(R.drawable.book_dark);
                video.setSave("1");
                Save save = new Save(video.getId(), video.getVideoTitle(), video.getVideoThumbnailB(),
                        video.getVideoUrl(), video.getVideoDescription(), video.getVideoDuration());
                db.iDao().addSave(save);
            }
        });


        binding.imgShareWhite.setOnClickListener(view -> {
            share();
        });

        binding.imgShareBlack.setOnClickListener(view -> {
            share();
        });

        binding.imgBackBlack.setOnClickListener(view -> finish());

        binding.imgBackWhite.setOnClickListener(view -> finish());

        List<Login> accountList = db.iDao().getAllAccount();
        if (accountList.size() > 0) {
            binding.btnWatch.setText(R.string.watch);
        } else {
            binding.btnWatch.setText(R.string.enter_and_watch);
        }

        binding.btnWatch.setOnClickListener(view -> {

            List<Login> accountList1 = db.iDao().getAllAccount();
            if (accountList1.size() > 0) {

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

            } else {
                Toast.makeText(VideoPlayerActivity.this, R.string.login_firs, Toast.LENGTH_SHORT).show();
            }
        });


        //check like & dislike
        List<LikedVideos> likedVideosList = db.iDao().getLikeVideos(video.getVideoTitle());

        if (likedVideosList.size() > 0) {
            LikedVideos likedVideos1 = likedVideosList.get(0);

            if (likedVideos1.isLike_state()) {
                binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.green), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.dark_gray), android.graphics.PorterDuff.Mode.SRC_IN);
            } else if (likedVideos1.isDislike_state()) {
                binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.dark_gray), android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }


        binding.imgSendComment.setOnClickListener(view -> {
            List<Login> loginList = db.iDao().getAllAccount();
            String text = binding.edtComment.getText().toString();
            if (loginList.size() > 0) {

                if (text.isEmpty()) {
                    Toast.makeText(VideoPlayerActivity.this, R.string.pls_enter_comment, Toast.LENGTH_SHORT).show();
                } else {
                    Login login = loginList.get(0);

                    String username = login.getName();

                    insertComment(text, username);

                    binding.progressComment.setVisibility(View.VISIBLE);
                    webserviceCaller.getSingleVideo(vid, new IResponseListener() {
                        @Override
                        public void onSuccess(Object responseMessage) {

                            if (responseMessage != null) {
                                SingleVideoModel singleVideoModel = (SingleVideoModel) responseMessage;

                                List<SingleVideo> singleVideoList = singleVideoModel.getAllInOneVideo();
                                singleVideo = singleVideoList.get(0);
                                userCommentList = singleVideo.getUserComments();


                                binding.recycleComments.setAdapter(new CommentAdapter(getApplicationContext(), userCommentList));
                                binding.recycleComments.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                                        RecyclerView.VERTICAL, false));

                                binding.progressComment.setVisibility(View.GONE);
                            } else {
                                binding.appBarLayout.setVisibility(View.GONE);
                                binding.scrollView.setVisibility(View.GONE);
                                binding.constraintNoSignal.setVisibility(View.VISIBLE);
                            }
                        }

                        @Override
                        public void onFailure(String onErrorMessage) {
                            binding.progressComment.setVisibility(View.GONE);
                        }
                    });


                }
            } else {
                Toast.makeText(VideoPlayerActivity.this, R.string.login_firs, Toast.LENGTH_SHORT).show();
            }
        });

        binding.imgLike.setOnClickListener(view -> {

            List<LikedVideos> likedVideosList1 = db.iDao().getLikeVideos(video.getVideoTitle());

            if (likedVideosList1.size() > 0) {
                LikedVideos likedVideos = likedVideosList1.get(0);

                if (likedVideos.isLike_state()) {
                    binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.green), android.graphics.PorterDuff.Mode.SRC_IN);
                    binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.dark_gray), android.graphics.PorterDuff.Mode.SRC_IN);
                } else {
                    binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.green), android.graphics.PorterDuff.Mode.SRC_IN);
                    binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.dark_gray), android.graphics.PorterDuff.Mode.SRC_IN);
                    boolean like = true;
                    boolean dislike = false;
                    db.iDao().updateLike(like, video.getVideoTitle());
                    db.iDao().updateDisLike(dislike, video.getVideoTitle());
                }
            } else {
                binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.green), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.dark_gray), android.graphics.PorterDuff.Mode.SRC_IN);
                LikedVideos likedVideos = new LikedVideos(video.getVideoTitle(), true, false);
                db.iDao().addLike(likedVideos);
            }

        });

        binding.imgDislike.setOnClickListener(view -> {
            List<LikedVideos> likedVideosList12 = db.iDao().getLikeVideos(video.getVideoTitle());

            if (likedVideosList12.size() > 0) {
                LikedVideos likedVideos = likedVideosList12.get(0);

                if (likedVideos.isLike_state()) {
                    binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.dark_gray), android.graphics.PorterDuff.Mode.SRC_IN);
                    binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
                    boolean like = false;
                    boolean dislike = true;
                    db.iDao().updateLike(like, video.getVideoTitle());
                    db.iDao().updateDisLike(dislike, video.getVideoTitle());
                } else {
                    binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.dark_gray), android.graphics.PorterDuff.Mode.SRC_IN);
                    binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                            R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);

                }

            } else {
                binding.imgLike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.dark_gray), android.graphics.PorterDuff.Mode.SRC_IN);
                binding.imgDislike.setColorFilter(ContextCompat.getColor(getApplicationContext(),
                        R.color.red), android.graphics.PorterDuff.Mode.SRC_IN);
                LikedVideos likedVideos = new LikedVideos(video.getVideoTitle(), false, true);
                db.iDao().addLike(likedVideos);
            }
        });

    }

    private void noCommentVideo() {

        binding.recycleComments.setVisibility(View.INVISIBLE);
        binding.cardNoComment.setVisibility(View.VISIBLE);
        webserviceCaller.getCmSingleVideo(vid, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                if (responseMessage != null) {
                    Log.e("failll &succusse", "");

                    CVideoModel cVideoModel = (CVideoModel) responseMessage;

                    List<CVideo> cVideoList = cVideoModel.getAllInOneVideo();
                    cVideo = cVideoList.get(0);

                    relatedList = cVideo.getRelated();

                    binding.recycleRelated.setAdapter(new RelatedAdapter(relatedList, getApplicationContext()));
                    binding.recycleRelated.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                            RecyclerView.HORIZONTAL, false));
                } else {
                    binding.appBarLayout.setVisibility(View.GONE);
                    binding.scrollView.setVisibility(View.GONE);
                    binding.constraintNoSignal.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String onErrorMessage) {

                Log.e("Failll & Failll", "");
            }
        });
    }

    private void commentVideo(Object responseMessage) {
        SingleVideoModel singleVideoModel = (SingleVideoModel) responseMessage;

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
    }


    private void insertComment(String text, String username) {
        webserviceCaller.insertComment(text, username, vid, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {

                if (responseMessage != null) {
                    Toast.makeText(VideoPlayerActivity.this, R.string.succefully_add_comment, Toast.LENGTH_SHORT).show();
                    binding.edtComment.setText(null);
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } else {
                    binding.appBarLayout.setVisibility(View.GONE);
                    binding.scrollView.setVisibility(View.GONE);
                    binding.constraintNoSignal.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String onErrorMessage) {
                Toast.makeText(VideoPlayerActivity.this, R.string.error_insert_comment, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkSaveWhite() {
        List<Save> saveList = db.iDao().getSaveVideos(video.getVideoTitle());

        if (saveList.size() > 0) {

            binding.imgSaveWhite.setBackgroundResource(R.drawable.ic_baseline_bookmark_24);
            binding.imgSaveBlack.setBackgroundResource(R.drawable.book_dark);
        } else {
            binding.imgSaveWhite.setBackgroundResource(R.drawable.ic_baseline_bookmark_border_24);

        }
    }


    private void share() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
        String shareMessage = "برای دانلود ";
        String advertise = "برنامه فیلیمو را نصب کنید";
        shareMessage = shareMessage + video.getVideoTitle() + advertise;
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "choose one"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.stop();
        }
    }


}