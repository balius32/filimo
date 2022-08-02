package com.balius.filimo.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.balius.filimo.R;
import com.balius.filimo.databinding.FragmentMyFilmsBinding;
import com.balius.filimo.fragments.myfilm.SavedFragment;
import com.balius.filimo.fragments.myfilm.WatchedFilmFragment;


public class MyFilmsFragment extends Fragment {
    FragmentMyFilmsBinding binding;

    public MyFilmsFragment() {
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMyFilmsBinding.inflate(getLayoutInflater());


        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new SavedFragment()).commit();
        binding.navMyfilms.getMenu().findItem(R.id.item_save).setChecked(true);

        binding.navMyfilms.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.item_save:

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new SavedFragment()).commit();
                    binding.navMyfilms.getMenu().findItem(R.id.item_save).setChecked(true);
                    break;

                case R.id.item_watched:

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new WatchedFilmFragment()).commit();
                    binding.navMyfilms.getMenu().findItem(R.id.item_watched).setChecked(true);
                    break;


            }
            return false;
        });

        return binding.getRoot();
    }
}