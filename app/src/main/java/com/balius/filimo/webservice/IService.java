package com.balius.filimo.webservice;

import com.balius.filimo.model.category.CategoryModel;
import com.balius.filimo.model.lastesvideo.VideoModel;
import com.balius.filimo.model.login.LoginModel;
import com.balius.filimo.model.sighnup.SighnupModel;
import com.balius.filimo.model.singelvideo.comment.SingleVideoModel;
import com.balius.filimo.model.singelvideo.nocomment.CVideoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IService {

    @GET("api.php?cat_list")
    Call<CategoryModel> getCategory();

    @GET("api.php?latest_video")
    Call<VideoModel> getLastestVideo();


    @GET("api.php")
    Call<VideoModel> searchCategory(@Query("cat_id") int cat_id);

    @GET("api.php?user_register")
    Call<SighnupModel> sighnup(@Query("name") String name , @Query("email") String email ,
                               @Query("password") String password ,@Query("phone") String phone);

    @GET("api.php?users_login")
    Call<LoginModel> login(@Query("email") String email, @Query("password") String password);

    @GET("api.php?forgot_pass")
    Call<SighnupModel> forgetPass(@Query("email") String email);


    @GET("api.php")
    Call<VideoModel>  searchVideo(@Query("search_text") String videoName);



    @GET("api.php")
    Call<SingleVideoModel> getSingleVideo(@Query("video_id") int id);

    @GET("api.php")
    Call<CVideoModel> getCSingleVideo(@Query("video_id") int id);

    @GET("api.php?video_comment")
    Call<SighnupModel> insertComment(
            @Query("comment_text") String tex
            ,@Query("video&user_name") String username
            ,@Query("post_id") int post_id);

}
