package com.balius.filimo.fragments.myfilm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balius.filimo.R;
import com.balius.filimo.adapter.SaveAdapter;
import com.balius.filimo.adapter.WatchedAdapter;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.FragmentWatchedFilmBinding;
import com.balius.filimo.model.Save;

import java.util.List;

public class WatchedFilmFragment extends Fragment {
    FragmentWatchedFilmBinding binding;
    Db db;

    public WatchedFilmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWatchedFilmBinding.inflate(getLayoutInflater());

        db = Db.getInstance(getActivity());

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.recycleWatched.setAdapter(new WatchedAdapter( db.iDao().getAllWatchedVideos(),getActivity()));
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
        binding.recycleWatched.setLayoutManager(manager);
        history();

    }

    public void history(){

        List<Save> saveList = db.iDao().getAllSaveVideos();

        if (saveList.size()>0){
            binding.relWatched.setVisibility(View.VISIBLE);
        }else {
            binding.relWatched.setVisibility(View.GONE);
            binding.constraintNoWatched.setVisibility(View.VISIBLE);
        }

    }
}