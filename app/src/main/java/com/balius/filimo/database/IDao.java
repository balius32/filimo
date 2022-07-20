package com.balius.filimo.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.balius.filimo.model.LikedVideos;
import com.balius.filimo.model.Save;
import com.balius.filimo.model.lastesvideo.Video;
import com.balius.filimo.model.login.Login;

import java.util.List;

@Dao
public interface IDao {

    @Insert
    long addVideo(Video video);


    @Query("delete from video where video_title = :name")
    void deleteVideo(String name);

    @Query("select * from video")
    List<Video> getAllVideos();

    @Insert
    long addAccount(Login login);

    @Query("select * from tbl_account")
    List<Login> getAllAccount();

    @Query("delete  from tbl_account where userId=:id")
    void logout(String id);


    @Query("select * from video where video_title = :name")
    List<Video> searchVideo(String name);

    @Query("select * from video where save = :save ")
    List<Video> getSavedVideos(String save);

    @Query("update video set save= :save")
    int updateSave(String save);


    @Insert
    long addSave(Save save);

    @Query("select * from tbl_save")
    List<Save> getAllSaveVideos();

    @Query("select * from tbl_save where video_title = :video_title")
    List<Save> getSaveVideos(String video_title);


//
//    @Insert
//    long addLike(LikedVideos like);
//
//    @Query("select * from tbl_like where video_title = :video_title")
//    List<LikedVideos> getLikeVideos(String video_title);
//
    @Query("delete  from tbl_save where video_id=:id")
    void deleteSave(String id);


}
