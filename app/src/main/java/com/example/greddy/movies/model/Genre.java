package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by greddy on 7/11/2017.
 */

public class Genre {

    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private final String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
