package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by greddy on 8/7/2017.
 */

public class ImageResponse {

    @SerializedName("backdrops")
    private List<Image> backdrops;

    public List<Image> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<Image> backdrops) {
        this.backdrops = backdrops;
    }
}
