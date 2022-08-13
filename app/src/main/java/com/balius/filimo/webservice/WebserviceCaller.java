package com.balius.filimo.webservice;

import android.util.Log;

import androidx.annotation.NonNull;

import com.balius.filimo.model.category.CategoryModel;
import com.balius.filimo.model.lastesvideo.VideoModel;
import com.balius.filimo.model.login.LoginModel;
import com.balius.filimo.model.sighnup.SighnupModel;
import com.balius.filimo.model.singelvideo.comment.SingleVideoModel;
import com.balius.filimo.model.singelvideo.nocomment.CVideo;
import com.balius.filimo.model.singelvideo.nocomment.CVideoModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebserviceCaller {

    IService iService;

    public WebserviceCaller() {

        iService = ApiClient.getClient().create(IService.class);
    }

    public void getCategory(IResponseListener listener) {
/*
        Call<CategoryModel> call = iService.getCategory();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<CategoryModel> call, @NonNull Response<CategoryModel> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CategoryModel> call, @NonNull Throwable t) {

                listener.onFailure(t.getMessage());
            }
        });*/

        Objects.requireNonNull(iService.getCategory()).enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(@NonNull Call<CategoryModel> call, @NonNull Response<CategoryModel> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CategoryModel> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }

    public void getLastestVideo(IResponseListener listener) {

        Call<VideoModel> call = iService.getLastestVideo();

        call.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(@NonNull Call<VideoModel> call, @NonNull Response<VideoModel> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<VideoModel> call, @NonNull Throwable t) {

                listener.onFailure(t.getMessage());
            }
        });
    }


    public void searchCategory(int id, IResponseListener listener) {

        Call<VideoModel> call = iService.searchCategory(id);

        call.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(@NonNull Call<VideoModel> call, @NonNull Response<VideoModel> response) {

                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<VideoModel> call, @NonNull Throwable t) {

                listener.onFailure(t.getMessage());
            }
        });
    }


    public void sighnup(String name, String email, String password, String phone, IResponseListener listener) {

        Call<SighnupModel> call = iService.sighnup(name, email, password, phone);

        call.enqueue(new Callback<SighnupModel>() {
            @Override
            public void onResponse(@NonNull Call<SighnupModel> call, @NonNull Response<SighnupModel> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<SighnupModel> call, @NonNull Throwable t) {

                listener.onFailure(t.getMessage());
            }
        });
    }

    public void login(String email, String password, IResponseListener listener) {

        Call<LoginModel> call = iService.login(email, password);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {

                listener.onFailure(t.getMessage());

            }
        });
    }

    public void forgetPass(String email, IResponseListener listener) {

        Call<SighnupModel> call = iService.forgetPass(email);

        call.enqueue(new Callback<SighnupModel>() {
            @Override
            public void onResponse(@NonNull Call<SighnupModel> call, @NonNull Response<SighnupModel> response) {
                listener.onSuccess(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<SighnupModel> call, @NonNull Throwable t) {

                listener.onFailure(t.getMessage());
            }
        });
    }


    public void searchVideo(String videoName, IResponseListener listener) {

        Call<VideoModel> call = iService.searchVideo(videoName);

        call.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(@NonNull Call<VideoModel> call, @NonNull Response<VideoModel> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<VideoModel> call, @NonNull Throwable t) {

                listener.onFailure(t.getMessage());

            }
        });
    }


    public void getSingleVideo(int id, IResponseListener listener) {

        Call<SingleVideoModel> call = iService.getSingleVideo(id);

        call.enqueue(new Callback<SingleVideoModel>() {
            @Override
            public void onResponse(@NonNull Call<SingleVideoModel> call, @NonNull Response<SingleVideoModel> response) {
                listener.onSuccess(response.body());
                Log.e("", "");
            }

            @Override
            public void onFailure(@NonNull Call<SingleVideoModel> call, @NonNull Throwable t) {
                listener.onFailure(t.getMessage());
            }
        });
    }


    public void getCmSingleVideo(int id, IResponseListener listener) {

        Call<CVideoModel> call = iService.getCSingleVideo(id);

        call.enqueue(new Callback<CVideoModel>() {
            @Override
            public void onResponse(@NonNull Call<CVideoModel> call, @NonNull Response<CVideoModel> response) {
                listener.onSuccess(response.body());
                Log.e("", "");
            }

            @Override
            public void onFailure(@NonNull Call<CVideoModel> call, @NonNull Throwable t) {

                listener.onFailure(t.getMessage());
            }
        });
    }

    public void insertComment(String cmText, String username, int videoId, IResponseListener listener) {

        Call<SighnupModel> call = iService.insertComment(cmText, username, videoId);

        call.enqueue(new Callback<SighnupModel>() {
            @Override
            public void onResponse(@NonNull Call<SighnupModel> call, @NonNull Response<SighnupModel> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<SighnupModel> call, @NonNull Throwable t) {

                listener.onFailure(t.getMessage());
            }
        });
    }

}
