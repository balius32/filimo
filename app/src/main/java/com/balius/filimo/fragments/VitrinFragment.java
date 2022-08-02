package com.balius.filimo.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    public View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentVitrinBinding.inflate(getLayoutInflater());
        webserviceCaller = new WebserviceCaller();
        db = Db.getInstance(getActivity());

        binding.progressLatest.setVisibility(View.VISIBLE);

        webserviceCaller.getLastestVideo(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {

                if (responseMessage !=null) {

                    VideoModel videoModel = (VideoModel) responseMessage;

                    binding.recycleLastestVideo.setAdapter(new VideoAdapter(getActivity(), videoModel.getAllInOneVideo()));
                    binding.recycleLastestVideo.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                    binding.progressLatest.setVisibility(View.GONE);
                } else {
                    binding.scrollView.setVisibility(View.GONE);
                    binding.appBarLayout.setVisibility(View.GONE);
                    binding.constraintNoSignal.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(String onErrorMessage) {
                binding.scrollView.setVisibility(View.GONE);
                binding.appBarLayout.setVisibility(View.GONE);
                binding.constraintNoSignal.setVisibility(View.VISIBLE);

            }
        });

        binding.progressPager.setVisibility(View.VISIBLE);
        binding.progressSpacial.setVisibility(View.VISIBLE);
        //spacial catId = 14
        webserviceCaller.searchCategory(14, new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {

                if (responseMessage !=null) {
                    VideoModel videoModel = (VideoModel) responseMessage;
                    binding.recycleSpacial.setAdapter(new HorizontalVideoAdapter(getActivity(), videoModel.getAllInOneVideo()));
                    binding.recycleSpacial.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                    binding.progressSpacial.setVisibility(View.GONE);

                    binding.imageSlider.setSliderAdapter(new SliderPagerAdapter(getActivity(), videoModel.getAllInOneVideo()));

                    binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);

                    binding.imageSlider.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
                    binding.imageSlider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_RTL);
                    binding.imageSlider.setIndicatorSelectedColor(Color.YELLOW);
                    binding.imageSlider.setIndicatorUnselectedColor(Color.DKGRAY);
                    binding.imageSlider.setScrollTimeInSec(4);
                    binding.imageSlider.startAutoCycle();

                    binding.progressPager.setVisibility(View.GONE);

                } else {
                    binding.scrollView.setVisibility(View.GONE);
                    binding.appBarLayout.setVisibility(View.GONE);
                    binding.constraintNoSignal.setVisibility(View.VISIBLE);

                }

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
                if (responseMessage !=null) {
                    VideoModel videoModel = (VideoModel) responseMessage;
                    binding.recycleSportVideo.setAdapter(new VideoAdapter(getActivity(), videoModel.getAllInOneVideo()));
                    binding.recycleSportVideo.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                    binding.progressSport.setVisibility(View.GONE);
                } else {
                    binding.scrollView.setVisibility(View.GONE);
                    binding.appBarLayout.setVisibility(View.GONE);
                    binding.constraintNoSignal.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(String onErrorMessage) {

            }
        });
        binding.relSpacial.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("catId", "14");
            startActivity(intent);
        });


        binding.relLastestVideo.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("catId", "85");
            startActivity(intent);

        });


        binding.relSportVideo.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("catId", "9");
            startActivity(intent);

        });

        binding.imgAccount.setOnClickListener(view -> {
            List<Login> loginList = db.iDao().getAllAccount();

            if (loginList.size() > 0) {
                Login login = loginList.get(0);
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("login", login);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        binding.imgSearch.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });


        binding.refreshLayout.setOnRefreshListener(() -> {
            requireActivity().finish();
            requireActivity().overridePendingTransition(0, 0);
            binding.refreshLayout.setVisibility(View.GONE);
            requireActivity().startActivity(requireActivity().getIntent());
            requireActivity().overridePendingTransition(0, 0);
            binding.refreshLayout.setVisibility(View.VISIBLE);
            binding.refreshLayout.setRefreshing(false);
        });

        binding.refreshLayout.setOnChildScrollUpCallback((parent, child) -> {

            if (binding.scrollView != null) {
                return binding.scrollView.canScrollVertically(-1);
            }
            return false;
        });

        return binding.getRoot();
    }
}