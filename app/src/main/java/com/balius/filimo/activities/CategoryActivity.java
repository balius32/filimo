package com.balius.filimo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.balius.filimo.R;
import com.balius.filimo.adapter.VideoAdapter;
import com.balius.filimo.databinding.ActivityCategoryBinding;
import com.balius.filimo.model.lastesvideo.Video;
import com.balius.filimo.model.lastesvideo.VideoModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;
    Bundle bundle ;
    Video video;
    WebserviceCaller webserviceCaller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundle = getIntent().getExtras();
        webserviceCaller = new WebserviceCaller();

        int id = bundle.getInt("catid");

        webserviceCaller.searchCategory(id, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                VideoModel videoModel = new VideoModel();
                videoModel = (VideoModel) responseMessage;

/*
                binding.recycleCategoryVideos.setAdapter(new VideoAdapter(getApplicationContext(),videoModel.getAllInOneVideo()));
                GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),3);
                binding.recycleCategoryVideos.setLayoutManager(manager);
                Log.e(""+responseMessage , "erooorrrr");*/

            }

            @Override
            public void onFailure(String onErrorMessage) {

                Log.e(""+onErrorMessage , "erooorrrr");

            }
        });

    }
}