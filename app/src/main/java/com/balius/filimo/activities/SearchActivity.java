package com.balius.filimo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.balius.filimo.R;
import com.balius.filimo.adapter.SearchAdapter;
import com.balius.filimo.databinding.ActivitySearchBinding;
import com.balius.filimo.model.lastesvideo.VideoModel;
import com.balius.filimo.webservice.IResponseListener;
import com.balius.filimo.webservice.WebserviceCaller;

public class SearchActivity extends AppCompatActivity {

    ActivitySearchBinding binding;
    WebserviceCaller webserviceCaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivitySearchBinding.inflate(getLayoutInflater());
       forceRTLIfSupported();
        setContentView(binding.getRoot());

        webserviceCaller =new WebserviceCaller();

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");


        binding.edtSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                performSearch();
                return true;
            }
            return false;

        });
        binding.imgBack.setOnClickListener(view -> finish());

    }

    private void performSearch() {
        webserviceCaller.searchVideo(binding.edtSearch.getText().toString(), new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                if (responseMessage !=null){


                VideoModel videoModel= (VideoModel) responseMessage;

                binding.recycleSearch.setAdapter(new SearchAdapter(getApplicationContext(),videoModel.getAllInOneVideo()));
                binding.recycleSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL,false));
                }else {
                    binding.constraintSearch.setVisibility(View.GONE);
                    binding.recycleSearch.setVisibility(View.GONE);
                    binding.constraintNoSignal.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(String onErrorMessage) {

            }
        });

    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}