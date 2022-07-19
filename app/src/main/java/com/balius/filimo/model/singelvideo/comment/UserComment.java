package com.balius.filimo.model.singelvideo.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserComment {

    @SerializedName("video_id")
    @Expose
    private String videoId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("comment_text")
    @Expose
    private String commentText;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

}
