package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greddy on 7/19/2017.
 */

public class VideoResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<Video> videoList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }
}
