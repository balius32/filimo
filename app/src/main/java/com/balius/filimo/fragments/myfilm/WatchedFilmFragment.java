package com.balius.filimo.fragments.myfilm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balius.filimo.R;
import com.balius.filimo.VarColumnGridLayoutManager;
import com.balius.filimo.adapter.SaveAdapter;
import com.balius.filimo.adapter.WatchedAdapter;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.FragmentWatchedFilmBinding;
import com.balius.filimo.model.Save;
import com.balius.filimo.model.Watched;

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

         //history();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.recycleWatched.setAdapter(new WatchedAdapter( db.iDao().getAllWatchedVideos(),getActivity()));

        final VarColumnGridLayoutManager layoutManager
                = new VarColumnGridLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        VarColumnGridLayoutManager.ColumnCountProvider columnProvider
                = new VarColumnGridLayoutManager.DefaultColumnCountProvider(getContext());
        layoutManager.setColumnCountProvider(columnProvider);

        binding.recycleWatched.setLayoutManager(layoutManager);

        // history();

        List<Watched> watchedList = db.iDao().getAllWatchedVideos();

        if (watchedList.size()>0){
            binding.relWatched.setVisibility(View.VISIBLE);
        }else {
            binding.relWatched.setVisibility(View.GONE);
            binding.constraintNoWatched.setVisibility(View.VISIBLE);
        }

    }

   /* public void history(){

        List<Save> saveList = db.iDao().getAllSaveVideos();

        if (saveList.size()>0){
            binding.relWatched.setVisibility(View.VISIBLE);
        }else {
            binding.relWatched.setVisibility(View.GONE);
            binding.constraintNoWatched.setVisibility(View.VISIBLE);
        }

    }*/
}