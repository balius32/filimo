package com.balius.filimo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
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


        binding.edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;

            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void performSearch() {


        webserviceCaller.searchVideo(binding.edtSearch.getText().toString(), new IResponseListener() {
            @Override
            public void onSuccess(Object responseMessage) {
                VideoModel videoModel= (VideoModel) responseMessage;

                binding.recycleSearch.setAdapter(new SearchAdapter(getApplicationContext(),videoModel.getAllInOneVideo()));
                binding.recycleSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        RecyclerView.VERTICAL,false));
            }

            @Override
            public void onFailure(String onErrorMessage) {

            }
        });

    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}