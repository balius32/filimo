package com.balius.filimo.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_save")
public class Save implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "video_id")
    private String video_id;

    @ColumnInfo(name = "video_title")
    private String video_title;

    @ColumnInfo(name = "video_image")
    private String video_image;

    @ColumnInfo(name = "video_url")
    private String video_url;

    @ColumnInfo(name = "video_description")
    private String video_description;

    @ColumnInfo(name = "video_time")
    private String video_time;

    public Save(int id, String video_id, String video_title, String video_image, String video_url, String video_description, String video_time) {
        this.id = id;
        this.video_id = video_id;
        this.video_title = video_title;
        this.video_image = video_image;
        this.video_url = video_url;
        this.video_description = video_description;
        this.video_time = video_time;
    }

    @Ignore
    public Save(String video_id, String video_title, String video_image, String video_url, String video_description, String video_time) {
        this.video_id = video_id;
        this.video_title = video_title;
        this.video_image = video_image;
        this.video_url = video_url;
        this.video_description = video_description;
        this.video_time = video_time;
    }

    public Save() {
    }

    protected Save(Parcel in) {
        id = in.readInt();
        video_id = in.readString();
        video_title = in.readString();
        video_image = in.readString();
    }

    public static final Creator<Save> CREATOR = new Creator<Save>() {
        @Override
        public Save createFromParcel(Parcel in) {
            return new Save(in);
        }

        @Override
        public Save[] newArray(int size) {
            return new Save[size];
        }
    };

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

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getVideo_description() {
        return video_description;
    }

    public void setVideo_description(String video_description) {
        this.video_description = video_description;
    }

    public String getVideo_time() {
        return video_time;
    }

    public void setVideo_time(String video_time) {
        this.video_time = video_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(video_id);
        parcel.writeString(video_title);
        parcel.writeString(video_image);
    }
}