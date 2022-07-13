package com.balius.filimo.model.lastesvideo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoModel {

    @SerializedName("ALL_IN_ONE_VIDEO")
    @Expose
    private List<Video> allInOneVideo = null;

    public List<Video> getAllInOneVideo() {
        return allInOneVideo;
    }

    public void setAllInOneVideo(List<Video> allInOneVideo) {
        this.allInOneVideo = allInOneVideo;
    }

}
