package com.balius.filimo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.balius.filimo.adapter.CategoryAdapter;
import com.balius.filimo.databinding.FragmentCategoryBinding;
import com.balius.filimo.model.category.CategoryModel;
import com.balius.filimo.model.category.VideoCategories;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;

import java.util.List;


public class CategoryFragment extends Fragment {
    FragmentCategoryBinding binding;
    WebserviceCaller webserviceCaller;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCategoryBinding.inflate(getLayoutInflater());

        webserviceCaller = new WebserviceCaller();

        webserviceCaller.getCategory(new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                CategoryModel ctmodel = new CategoryModel();
                ctmodel = (CategoryModel) responseMessage;


                binding.recycleCategory.setAdapter(new CategoryAdapter(ctmodel.getAllInOneVideo(),getActivity()));

                binding.recycleCategory.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false));

                Log.e(""+responseMessage,"");
            }

            @Override
            public void onFailure(String onErrorMessage) {

                Log.e(""+onErrorMessage,"");

            }
        });




        return binding.getRoot();
    }
}