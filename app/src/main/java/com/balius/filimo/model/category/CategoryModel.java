package com.balius.filimo.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryModel {

    @SerializedName("ALL_IN_ONE_VIDEO")
    @Expose
    private List<VideoCategories> allInOneVideo = null;

    public List<VideoCategories> getAllInOneVideo() {
        return allInOneVideo;
    }

    public void setAllInOneVideo(List<VideoCategories> allInOneVideo) {
        this.allInOneVideo = allInOneVideo;
    }
}
