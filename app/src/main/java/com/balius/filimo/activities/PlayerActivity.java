package com.balius.filimo.activities;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.balius.filimo.R;
import com.balius.filimo.databinding.ActivityPlayerBinding;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;

public class PlayerActivity extends AppCompatActivity {
    ActivityPlayerBinding binding;

    AppCompatImageView lock;
    AppCompatImageView fullscreen;
    AppCompatImageView img_open_lock;
    AppCompatImageView img_back;
    AppCompatTextView txt_name;
    ProgressBar progressbar;

    ExoPlayer player;

    Bundle bundle;
    String url;
    boolean isLocked = false;
    boolean isFullScreen = false;

    String videoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundle = getIntent().getExtras();
        url = bundle.getString("url");
        videoName = bundle.getString("title");

        lock = findViewById(R.id.exo_lock);
        fullscreen = findViewById(R.id.btn_fullscreen);
        progressbar = findViewById(R.id.progressbar);
        img_open_lock = findViewById(R.id.img_open_lock);
        img_back = findViewById(R.id.img_back);
        txt_name = findViewById(R.id.txt_name);

        img_open_lock.setVisibility(View.GONE);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        txt_name.setText(videoName);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLocked = !isLocked;
                lockScreen(isLocked);


            }
        });

        img_open_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLocked = !isLocked;
                lockScreen(isLocked);
            }
        });


        fullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFullScreen) {
                    fullscreen.setBackgroundResource(R.drawable.ic_baseline_fullscreen_exit_24);
                    binding.playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);

                } else {
                    fullscreen.setBackgroundResource(R.drawable.ic_baseline_fullscreen_24);
                    binding.playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
                }
                isFullScreen = !isFullScreen;
            }
        });

        player = new ExoPlayer.Builder(this)
                .setSeekBackIncrementMs(10000)
                .setSeekForwardIncrementMs(10000)
                .build();

        player.addListener(new Player.Listener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                Player.Listener.super.onPlayerStateChanged(playWhenReady, playbackState);

                if (playbackState == Player.STATE_BUFFERING) {
                    progressbar.setVisibility(View.VISIBLE);

                } else if (playbackState == Player.STATE_READY) {
                    progressbar.setVisibility(View.GONE);
                }
            }
        });

        MediaItem item = MediaItem.fromUri(Uri.parse(url));
        player.setMediaItem(item);
        binding.playerView.setPlayer(player);

        player.play();


    }

    private void lockScreen(boolean loked) {
        RelativeLayout rel_tools = findViewById(R.id.rel_tools);

        if (loked) {
            rel_tools.setVisibility(View.INVISIBLE);
            img_open_lock.setVisibility(View.VISIBLE);

        } else {
            rel_tools.setVisibility(View.VISIBLE);
            img_open_lock.setVisibility(View.GONE);

        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (player != null) {
            player.stop();
        }
    }
}