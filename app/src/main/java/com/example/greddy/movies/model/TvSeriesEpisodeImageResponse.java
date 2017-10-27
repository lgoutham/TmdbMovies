package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by greddy on 8/8/2017.
 */

public class TvSeriesEpisodeImageResponse {

    @SerializedName("posters")
    private List<Image> posters;

    public List<Image> getPosters() {
        return posters;
    }

    public void setPosters(List<Image> posters) {
        this.posters = posters;
    }
}
