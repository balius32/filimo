package com.balius.filimo.model.singelvideo.nocomment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CVideoModel {
    @SerializedName("ALL_IN_ONE_VIDEO")
    @Expose
    private List<CVideo> allInOneVideo = null;

    public List<CVideo> getAllInOneVideo() {
        return allInOneVideo;
    }

    public void setAllInOneVideo(List<CVideo> allInOneVideo) {
        this.allInOneVideo = allInOneVideo;
    }

}
