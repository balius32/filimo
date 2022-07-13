package com.balius.filimo.fragments.myfilm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balius.filimo.R;
import com.balius.filimo.databinding.FragmentWatchedFilmBinding;

public class WatchedFilmFragment extends Fragment {
FragmentWatchedFilmBinding binding;

    public WatchedFilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWatchedFilmBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}