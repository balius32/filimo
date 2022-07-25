package com.balius.filimo.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.balius.filimo.R;
import com.balius.filimo.adapter.VideoCategoryAdapter;
import com.balius.filimo.adapter.VideoCategoryHAdapter;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundle = getIntent().getExtras();
        webserviceCaller = new WebserviceCaller();

        String id = bundle.getString("catId");
        int catId = Integer.parseInt(id);

        if (catId==85){

            binding.txtCategoryName.setText(R.string.lastest_video);
            binding.lblCatName.setText(R.string.lastest_video);


            binding.progressHorizontal.setVisibility(View.VISIBLE);
            binding.progressVertical.setVisibility(View.VISIBLE);
            webserviceCaller.getLastestVideo(new IResponseListener() {
                @Override
                public void onSuccess(Object responseMessage) {
                    VideoModel videoModel = (VideoModel) responseMessage;
                    video = videoModel.getAllInOneVideo().get(0);

                    binding.recycleVertical.setAdapter(new VideoCategoryAdapter(videoModel.getAllInOneVideo(), getApplicationContext()));
                    GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),3);
                    binding.recycleVertical.setLayoutManager(manager);
                    binding.progressVertical.setVisibility(View.GONE);


                    binding.recycleHorizontal.setAdapter(new VideoCategoryHAdapter(videoModel.getAllInOneVideo(), getApplicationContext()));
                    LinearLayoutManager manager1 = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false);
                    binding.recycleHorizontal.setLayoutManager(manager1);
                    binding.progressHorizontal.setVisibility(View.GONE);



                }

                @Override
                public void onFailure(String onErrorMessage) {

                }
            });

        }else {
            binding.progressHorizontal.setVisibility(View.VISIBLE);
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


                    binding.recycleHorizontal.setAdapter(new VideoCategoryHAdapter(videoModel.getAllInOneVideo(), getApplicationContext()));
                    LinearLayoutManager manager1 = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL,false);
                    binding.recycleHorizontal.setLayoutManager(manager1);
                    binding.progressHorizontal.setVisibility(View.GONE);

                    binding.txtCategoryName.setText(video.getCategoryName());
                    binding.lblCatName.setText(video.getCategoryName());

                }

                @Override
                public void onFailure(String onErrorMessage) {

                    Log.e("" + onErrorMessage, "erooorrrr");

                }
            });

        }


        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}