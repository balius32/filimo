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

        webserviceCaller.getLastestVideo(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                Log.e(""+responseMessage , " eeeeeee");

                VideoModel videoModel= new VideoModel();
                videoModel = (VideoModel) responseMessage;


                binding.recycleLastestVideo.setAdapter(new VideoAdapter(getActivity(),videoModel.getAllInOneVideo()));
                binding.recycleLastestVideo.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));
            }

            @Override
            public void onFailure(String onErrorMessage) {

            }
        });



        webserviceCaller.searchSpacialVideos(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                Log.e(""+responseMessage.toString() , " eeeeeee");

                VideoModel videoModel= new VideoModel();
                videoModel = (VideoModel) responseMessage;

                binding.recycleSpacial.setAdapter(new HorizontalVideoAdapter(getActivity(),videoModel.getAllInOneVideo() ));
                binding.recycleSpacial.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

              //  binding.pager.setAdapter(new PageAdapter(getActivity(),videoModel.getAllInOneVideo()));
            }

            @Override
            public void onFailure(String onErrorMessage) {

            }
        });

        webserviceCaller.searchSportVideos(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {


                VideoModel videoModel= new VideoModel();
                videoModel = (VideoModel) responseMessage;

                binding.recycleSportVideo.setAdapter(new VideoAdapter(getActivity(),videoModel.getAllInOneVideo() ));
                binding.recycleSportVideo.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL,false));

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
                intent.putExtra("catid",14);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }
}