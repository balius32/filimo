package com.balius.filimo.model.lastesvideo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "video")
public class Video implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int vId;

    @SerializedName("id")
    @Expose
    @ColumnInfo(name = "id")
    private String id;
    @SerializedName("cat_id")
    @Expose
    @ColumnInfo(name = "cat_id")
    private String catId;
    @SerializedName("video_type")
    @Expose
    @ColumnInfo(name = "video_type")
    private String videoType;
    @SerializedName("video_title")
    @Expose
    @ColumnInfo(name = "video_title")
    private String videoTitle;
    @SerializedName("video_url")
    @Expose
    @ColumnInfo(name = "video_url")
    private String videoUrl;
    @SerializedName("video_id")
    @Expose
    @ColumnInfo(name = "video_id")
    private String videoId;
    @SerializedName("video_thumbnail_b")
    @Expose
    @ColumnInfo(name = "video_thumbnail_b")
    private String videoThumbnailB;
    @SerializedName("video_thumbnail_s")
    @Expose
    @ColumnInfo(name = "video_thumbnail_s")
    private String videoThumbnailS;
    @SerializedName("video_duration")
    @Expose
    @ColumnInfo(name = "video_duration")
    private String videoDuration;
    @SerializedName("video_description")
    @Expose
    private String videoDescription;
    @SerializedName("rate_avg")
    @Expose
    @ColumnInfo(name = "rate_avg")
    private String rateAvg;
    @SerializedName("totel_viewer")
    @Expose
    @ColumnInfo(name = "totel_viewer")
    private String totelViewer;
    @SerializedName("cid")
    @Expose
    @ColumnInfo(name = "cid")
    private String cid;
    @SerializedName("category_name")
    @Expose
    @ColumnInfo(name = "category_name")
    private String categoryName;
    @SerializedName("category_image")
    @Expose
    @ColumnInfo(name = "category_image")
    private String categoryImage;
    @SerializedName("category_image_thumb")
    @Expose
    @ColumnInfo(name = "category_image_thumb")
    private String categoryImageThumb;

    @ColumnInfo(name = "save")
    private String save = "0";

    @ColumnInfo(name = "like")
    private String like = "0";


    @ColumnInfo(name = "dislike")
    private String dislike = "0";

    @ColumnInfo(name = "watched")
    private String watched = "0";

    protected Video(Parcel in) {
        id = in.readString();
        catId = in.readString();
        videoType = in.readString();
        videoTitle = in.readString();
        videoUrl = in.readString();
        videoId = in.readString();
        videoThumbnailB = in.readString();
        videoThumbnailS = in.readString();
        videoDuration = in.readString();
        videoDescription = in.readString();
        rateAvg = in.readString();
        totelViewer = in.readString();
        cid = in.readString();
        categoryName = in.readString();
        categoryImage = in.readString();
        categoryImageThumb = in.readString();
    }


    public Video(String id, String catId, String videoType, String videoTitle, String videoUrl, String videoId, String videoThumbnailB, String videoThumbnailS, String videoDuration, String videoDescription, String rateAvg, String totelViewer, String cid, String categoryName, String categoryImage, String categoryImageThumb) {
        this.id = id;
        this.catId = catId;
        this.videoType = videoType;
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
        this.videoId = videoId;
        this.videoThumbnailB = videoThumbnailB;
        this.videoThumbnailS = videoThumbnailS;
        this.videoDuration = videoDuration;
        this.videoDescription = videoDescription;
        this.rateAvg = rateAvg;
        this.totelViewer = totelViewer;
        this.cid = cid;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryImageThumb = categoryImageThumb;
    }

    @Ignore
    public Video(int vId, String id, String catId, String videoType, String videoTitle, String videoUrl, String videoId, String videoThumbnailB, String videoThumbnailS, String videoDuration, String videoDescription, String rateAvg, String totelViewer, String cid, String categoryName, String categoryImage, String categoryImageThumb, String save, String like, String dislike, String watched) {
        this.vId = vId;
        this.id = id;
        this.catId = catId;
        this.videoType = videoType;
        this.videoTitle = videoTitle;
        this.videoUrl = videoUrl;
        this.videoId = videoId;
        this.videoThumbnailB = videoThumbnailB;
        this.videoThumbnailS = videoThumbnailS;
        this.videoDuration = videoDuration;
        this.videoDescription = videoDescription;
        this.rateAvg = rateAvg;
        this.totelViewer = totelViewer;
        this.cid = cid;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.categoryImageThumb = categoryImageThumb;
        this.save = save;
        this.like = like;
        this.dislike = dislike;
        this.watched = watched;
    }

    public Video() {
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public int getvId() {
        return vId;
    }

    public void setvId(int vId) {
        this.vId = vId;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryImageThumb() {
        return categoryImageThumb;
    }

    public void setCategoryImageThumb(String categoryImageThumb) {
        this.categoryImageThumb = categoryImageThumb;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(catId);
        parcel.writeString(videoType);
        parcel.writeString(videoTitle);
        parcel.writeString(videoUrl);
        parcel.writeString(videoId);
        parcel.writeString(videoThumbnailB);
        parcel.writeString(videoThumbnailS);
        parcel.writeString(videoDuration);
        parcel.writeString(videoDescription);
        parcel.writeString(rateAvg);
        parcel.writeString(totelViewer);
        parcel.writeString(cid);
        parcel.writeString(categoryName);
        parcel.writeString(categoryImage);
        parcel.writeString(categoryImageThumb);
    }
}
