package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by greddy on 8/3/2017.
 */

class Stars {

    @SerializedName("character")
    private String character;
    @SerializedName("name")
    private String name;
    @SerializedName("profile_path")
    private String profilePath;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
