package com.balius.filimo.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_like")
public class LikedVideos {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "video_title")
    private String video_title;

    @ColumnInfo(name = "like_state")
    private boolean like_state  ;

    @ColumnInfo(name = "dislike_state")
    private boolean dislike_state ;

    public LikedVideos(int id, String video_title, boolean like_state, boolean dislike_state) {
        this.id = id;
        this.video_title = video_title;
        this.like_state = like_state;
        this.dislike_state = dislike_state;
    }

    @Ignore
    public LikedVideos(String video_title, boolean like_state, boolean dislike_state) {
        this.video_title = video_title;
        this.like_state = like_state;
        this.dislike_state = dislike_state;
    }

    public LikedVideos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public boolean isLike_state() {
        return like_state;
    }

    public void setLike_state(boolean like_state) {
        this.like_state = like_state;
    }

    public boolean isDislike_state() {
        return dislike_state;
    }

    public void setDislike_state(boolean dislike_state) {
        this.dislike_state = dislike_state;
    }
}
