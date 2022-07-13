package com.balius.filimo.fragments.myfilm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.balius.filimo.R;
import com.balius.filimo.adapter.VideoAdapter;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.FragmentSavedBinding;

public class SavedFragment extends Fragment {
    FragmentSavedBinding binding;

    Db db;

    public SavedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSavedBinding.inflate(getLayoutInflater());

        db = Db.getInstance(getActivity());

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

//        binding.recycleSaved.setAdapter(new VideoAdapter(getActivity(), db.iDao().getSavedVideos("1")));
//        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
//        binding.recycleSaved.setLayoutManager(manager);

    }
}