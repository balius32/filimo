package com.balius.filimo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.balius.filimo.activities.CategoryActivity;
import com.balius.filimo.adapter.HorizontalVideoAdapter;
import com.balius.filimo.adapter.PageAdapter;
import com.balius.filimo.adapter.VideoAdapter;
import com.balius.filimo.databinding.FragmentVitrinBinding;
import com.balius.filimo.model.lastesvideo.VideoModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;


public class VitrinFragment extends Fragment {

    FragmentVitrinBinding binding;
    WebserviceCaller webserviceCaller;

    public VitrinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentVitrinBinding.inflate(getLayoutInflater());
        webserviceCaller = new WebserviceCaller();



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
                binding.constraintNoSignal.setVisibility(View.VISIBLE);


            }
        });


        binding.progressPager.setVisibility(View.VISIBLE);
        binding.progressSpacial.setVisibility(View.VISIBLE);
        webserviceCaller.searchSpacialVideos(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                Log.e("" + responseMessage.toString(), " eeeeeee");

                VideoModel videoModel;
                videoModel = (VideoModel) responseMessage;

                binding.recycleSpacial.setAdapter(new HorizontalVideoAdapter(getActivity(), videoModel.getAllInOneVideo()));
                binding.recycleSpacial.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                binding.progressSpacial.setVisibility(View.GONE);


                binding.pager.setAdapter(new PageAdapter(getActivity(), videoModel.getAllInOneVideo()));
                binding.springDotsIndicator.setViewPager(binding.pager);

                binding.progressPager.setVisibility(View.GONE);}

            @Override
            public void onFailure(String onErrorMessage) {

            }
        });

        binding.progressSport.setVisibility(View.VISIBLE);
        webserviceCaller.searchSportVideos(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {


                VideoModel videoModel  =(VideoModel) responseMessage;
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


        return binding.getRoot();
    }
}