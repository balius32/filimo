package com.balius.filimo.webservice;

import com.balius.filimo.model.category.CategoryModel;
import com.balius.filimo.model.lastesvideo.VideoModel;
import com.balius.filimo.model.login.LoginModel;
import com.balius.filimo.model.sighnup.SighnupModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IService {

    @GET("api.php?cat_list")
    Call<CategoryModel> getCategory();

    @GET("api.php?latest_video")
    Call<VideoModel> getLastestVideo();


   @GET("api.php?cat_id=14")
    Call<VideoModel> spacialCategory();

    @GET("api.php?cat_id=9")
    Call<VideoModel> sportCategory();


    @GET("api.php")
    Call<VideoModel> searchCategory(@Query("cat_id") int cat_id);

    @GET("api.php?user_register")
    Call<SighnupModel> sighnup(@Query("name") String name , @Query("email") String email ,
                               @Query("password") String password ,@Query("phone") int phone);

    @GET("api.php?users_login")
    Call<LoginModel> login(@Query("email") String email, @Query("password") String password);

    @GET("api.php?forgot_pass&email=john@gmail.com")
    Call<SighnupModel> forgetPass(@Query("email") String email);


    @GET("api.php?search_text=the")
    Call<VideoModel>  searchVideo(@Query("search_text") String videoName);


}
