package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by greddy on 7/15/2017.
 */

public class GenreResponse {

    @SerializedName("genres")
    private List<Genre> genreList;

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }
}
