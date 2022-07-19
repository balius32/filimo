package com.balius.filimo.model.singelvideo.comment;

import com.balius.filimo.model.singelvideo.comment.SingleVideo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SingleVideoModel {


    @SerializedName("ALL_IN_ONE_VIDEO")
    @Expose
    private List<SingleVideo> allInOneVideo = null;

    public List<SingleVideo> getAllInOneVideo() {
        return allInOneVideo;
    }

    public void setAllInOneVideo(List<SingleVideo> allInOneVideo) {
        this.allInOneVideo = allInOneVideo;
    }


}
