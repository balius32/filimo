package com.balius.filimo.fragments.myfilm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.balius.filimo.adapter.SaveAdapter;
import com.balius.filimo.database.Db;
import com.balius.filimo.databinding.FragmentSavedBinding;
import com.balius.filimo.model.Save;

import java.util.List;

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
        binding.recycleSaved.setAdapter(new SaveAdapter(getActivity(), db.iDao().getAllSaveVideos()));
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
        binding.recycleSaved.setLayoutManager(manager);
        checkNull();

    }

    public void checkNull(){

        List<Save> saveList = db.iDao().getAllSaveVideos();

        if (saveList.size()>0){
            binding.relSaveVideo.setVisibility(View.VISIBLE);
        }else {
            binding.relSaveVideo.setVisibility(View.GONE);
            binding.constraintNoSave.setVisibility(View.VISIBLE);
        }

    }
}