package com.balius.filimo.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginModel {


    @SerializedName("ALL_IN_ONE_VIDEO")
    @Expose
    private List<Login> allInOneVideo = null;

    public List<Login> getAllInOneVideo() {
        return allInOneVideo;
    }

    public void setAllInOneVideo(List<Login> allInOneVideo) {
        this.allInOneVideo = allInOneVideo;
    }

}
