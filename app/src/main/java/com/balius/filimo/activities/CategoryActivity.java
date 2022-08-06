package com.balius.filimo.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;

import com.balius.filimo.R;
import com.balius.filimo.VarColumnGridLayoutManager;
import com.balius.filimo.adapter.VideoCategoryAdapter;
import com.balius.filimo.databinding.ActivityCategoryBinding;
import com.balius.filimo.model.lastesvideo.Video;
import com.balius.filimo.model.lastesvideo.VideoModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;
    Bundle bundle;
    Video video;
    WebserviceCaller webserviceCaller;
    int catId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundle = getIntent().getExtras();
        webserviceCaller = new WebserviceCaller();

        String id = bundle.getString("catId");
        catId = Integer.parseInt(id);

        if (catId==85){
            latestVideo();
        }else {
          getCategory();
        }

        binding.imgBack.setOnClickListener(view ->
                finish());
    }
    private void latestVideo(){

        binding.txtCategoryName.setText(R.string.lastest_video);
        binding.lblCatName.setText(R.string.lastest_video);


        binding.progressVertical.setVisibility(View.VISIBLE);
        webserviceCaller.getLastestVideo(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                VideoModel videoModel = (VideoModel) responseMessage;
                video = videoModel.getAllInOneVideo().get(0);

                binding.recycleVertical.setAdapter(new VideoCategoryAdapter(videoModel.getAllInOneVideo(), getApplicationContext()));

                final VarColumnGridLayoutManager layoutManager
                        = new VarColumnGridLayoutManager(getApplicationContext(), OrientationHelper.VERTICAL, false);
                VarColumnGridLayoutManager.ColumnCountProvider columnProvider
                        = new VarColumnGridLayoutManager.DefaultColumnCountProvider(getApplicationContext());
                layoutManager.setColumnCountProvider(columnProvider);

                binding.recycleVertical.setLayoutManager(layoutManager);
                binding.progressVertical.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String onErrorMessage) {

            }
        });

    }

    private void getCategory(){

        binding.progressVertical.setVisibility(View.VISIBLE);
        webserviceCaller.searchCategory(catId, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                VideoModel videoModel = (VideoModel) responseMessage;
                video = videoModel.getAllInOneVideo().get(0);

                binding.recycleVertical.setAdapter(new VideoCategoryAdapter(videoModel.getAllInOneVideo(), getApplicationContext()));
                GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),3);
                binding.recycleVertical.setLayoutManager(manager);
                binding.progressVertical.setVisibility(View.GONE);

                binding.txtCategoryName.setText(video.getCategoryName());
                binding.lblCatName.setText(video.getCategoryName());

            }

            @Override
            public void onFailure(String onErrorMessage) {

                Log.e("" + onErrorMessage, "erooorrrr");

            }
        });

    }
}