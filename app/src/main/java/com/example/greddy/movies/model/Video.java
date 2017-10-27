package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by greddy on 7/19/2017.
 */

public class Video {

    @SerializedName("id")
    private String videoId;
    @SerializedName("key")
    private String videoKey;
    @SerializedName("name")
    private String videoName;
    @SerializedName("type")
    private String videoType;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoKey() {
        return videoKey;
    }

    public void setVideoKey(String videoKey) {
        this.videoKey = videoKey;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
}
