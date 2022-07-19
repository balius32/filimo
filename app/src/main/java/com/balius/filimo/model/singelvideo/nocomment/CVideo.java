package com.balius.filimo.model.singelvideo.nocomment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CVideo {

    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("video_type")
    @Expose
    private String videoType;
    @SerializedName("video_title")
    @Expose
    private String videoTitle;
    @SerializedName("video_url")
    @Expose
    private String videoUrl;
    @SerializedName("video_id")
    @Expose
    private String videoId;
    @SerializedName("video_thumbnail_b")
    @Expose
    private String videoThumbnailB;
    @SerializedName("video_thumbnail_s")
    @Expose
    private String videoThumbnailS;
    @SerializedName("video_duration")
    @Expose
    private String videoDuration;
    @SerializedName("video_description")
    @Expose
    private String videoDescription;
    @SerializedName("rate_avg")
    @Expose
    private String rateAvg;
    @SerializedName("totel_viewer")
    @Expose
    private String totelViewer;
    @SerializedName("related")
    @Expose
    private List<CRelated> related = null;
    @SerializedName("user_comments")
    @Expose
    private List<String> userComments = null;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoThumbnailB() {
        return videoThumbnailB;
    }

    public void setVideoThumbnailB(String videoThumbnailB) {
        this.videoThumbnailB = videoThumbnailB;
    }

    public String getVideoThumbnailS() {
        return videoThumbnailS;
    }

    public void setVideoThumbnailS(String videoThumbnailS) {
        this.videoThumbnailS = videoThumbnailS;
    }

    public String getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(String videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getRateAvg() {
        return rateAvg;
    }

    public void setRateAvg(String rateAvg) {
        this.rateAvg = rateAvg;
    }

    public String getTotelViewer() {
        return totelViewer;
    }

    public void setTotelViewer(String totelViewer) {
        this.totelViewer = totelViewer;
    }

    public List<CRelated> getRelated() {
        return related;
    }

    public void setRelated(List<CRelated> related) {
        this.related = related;
    }

    public List<String> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<String> userComments) {
        this.userComments = userComments;
    }
}
