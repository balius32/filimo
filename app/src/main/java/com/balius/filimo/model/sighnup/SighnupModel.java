package com.balius.filimo.model.sighnup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SighnupModel {
    @SerializedName("ALL_IN_ONE_VIDEO")
    @Expose
    private List<Sighnup> allInOneVideo = null;

    public List<Sighnup> getAllInOneVideo() {
        return allInOneVideo;
    }

    public void setAllInOneVideo(List<Sighnup> allInOneVideo) {
        this.allInOneVideo = allInOneVideo;
    }

}
