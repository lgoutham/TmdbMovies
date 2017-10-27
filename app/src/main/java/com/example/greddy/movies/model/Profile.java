package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by greddy on 7/18/2017.
 */

public class Profile {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("gender")
    private int gender;
    @SerializedName("profile_path")
    private String profilePath;

    public Profile(int id, String name, int gender, String profilePath) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.profilePath = profilePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
