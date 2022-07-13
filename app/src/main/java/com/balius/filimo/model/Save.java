package com.balius.filimo.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_save")
public class Save {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "video_id")
    private String video_id;

    @ColumnInfo(name = "video_title")
    private String video_title;

    @ColumnInfo(name = "video_image")
    private String video_image;


    public Save(int id, String video_id, String video_title, String video_image) {
        this.id = id;
        this.video_id = video_id;
        this.video_title = video_title;
        this.video_image = video_image;
    }

    @Ignore
    public Save(String video_id, String video_title, String video_image) {
        this.video_id = video_id;
        this.video_title = video_title;
        this.video_image = video_image;
    }

    public Save() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_image() {
        return video_image;
    }

    public void setVideo_image(String video_image) {
        this.video_image = video_image;
    }
}