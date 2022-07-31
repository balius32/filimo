package com.balius.filimo.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.balius.filimo.R;
import com.balius.filimo.activities.CategoryActivity;
import com.balius.filimo.activities.ProfileActivity;
import com.balius.filimo.activities.SearchActivity;
import com.balius.filimo.activities.UserProfileActivity;
import com.balius.filimo.adapter.HorizontalVideoAdapter;
import com.balius.filimo.adapter.SliderPagerAdapter;
import com.balius.filimo.adapter.VideoAdapter;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.FragmentVitrinBinding;
import com.balius.filimo.model.lastesvideo.VideoModel;
import com.balius.filimo.model.login.Login;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;
import com.google.android.material.appbar.AppBarLayout;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;


public class VitrinFragment extends Fragment {

    FragmentVitrinBinding binding;
    WebserviceCaller webserviceCaller;
    Db db;

    public VitrinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentVitrinBinding.inflate(getLayoutInflater());
        webserviceCaller = new WebserviceCaller();
        db = Db.getInstance(getActivity());

        binding.progressLatest.setVisibility(View.VISIBLE);

        webserviceCaller.getLastestVideo(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                Log.e("" + responseMessage, " eeeeeee");

                VideoModel videoModel = (VideoModel) responseMessage;

                binding.recycleLastestVideo.setAdapter(new VideoAdapter(getActivity(), videoModel.getAllInOneVideo()));
                binding.recycleLastestVideo.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                binding.progressLatest.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String onErrorMessage) {
                binding.scrollView.setVisibility(View.GONE);
                binding.appBarLayout.setVisibility(View.GONE);
                binding.constraintNoSignal.setVisibility(View.VISIBLE);

            }
        });

        // binding.progressPager.setVisibility(View.VISIBLE);
        binding.progressSpacial.setVisibility(View.VISIBLE);
        //spacial catId = 14
        webserviceCaller.searchCategory(14, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                VideoModel videoModel = (VideoModel) responseMessage;

                binding.recycleSpacial.setAdapter(new HorizontalVideoAdapter(getActivity(), videoModel.getAllInOneVideo()));
                binding.recycleSpacial.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                binding.progressSpacial.setVisibility(View.GONE);

/*
                binding.pager.setAdapter(new PageAdapter(getActivity(), videoModel.getAllInOneVideo()));
                binding.springDotsIndicator.setViewPager(binding.pager);*/


                binding.imageSlider.setSliderAdapter(new SliderPagerAdapter(getActivity(), videoModel.getAllInOneVideo()));

                binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                binding.imageSlider.setIndicatorSelectedColor(Color.YELLOW);
                binding.imageSlider.setIndicatorUnselectedColor(Color.DKGRAY);
                binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
                binding.imageSlider.startAutoCycle();

                // binding.progressPager.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String onErrorMessage) {

            }
        });
        binding.progressSport.setVisibility(View.VISIBLE);

        //sport catId = 9
        webserviceCaller.searchCategory(9, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                VideoModel videoModel = (VideoModel) responseMessage;
                binding.recycleSportVideo.setAdapter(new VideoAdapter(getActivity(), videoModel.getAllInOneVideo()));
                binding.recycleSportVideo.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                binding.progressSport.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String onErrorMessage) {

            }
        });
        binding.relSpacial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("catId", "14");
                startActivity(intent);
            }
        });


        binding.relLastestVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("catId", "85");
                startActivity(intent);

            }
        });


        binding.relSportVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoryActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("catId", "9");
                startActivity(intent);

            }
        });

        binding.imgAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Login> loginList = db.iDao().getAllAccount();
                Login login = new Login();

                if (loginList.size() > 0) {
                    login = loginList.get(0);
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    intent.putExtra("login", login);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                    startActivity(intent);
                }

            }
        });

        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });




        return binding.getRoot();
    }
}