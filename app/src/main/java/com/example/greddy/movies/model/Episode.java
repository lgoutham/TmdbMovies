package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by greddy on 8/3/2017.
 */

public class Episode {

    @SerializedName("air_date")
    private String airDate;
    @SerializedName("crew")
    private List<Crew> crewList;
    @SerializedName("episode_number")
    private int episodeNumber;
    @SerializedName("guest_stars")
    private List<Stars> starsList;
    @SerializedName("name")
    private String name;
    @SerializedName("overview")
    private String overview;
    @SerializedName("id")
    private int id;
    @SerializedName("still_path")
    private String stillPath;
    @SerializedName("vote_average")
    private Float voteAverage;
    @SerializedName("vote_count")
    private int voteCount;

    public Episode(String airDate, List<Crew> crewList, int episodeNumber, List<Stars> starsList, String name, String overview, int id, String stillPath, Float voteAverage, int voteCount) {
        this.airDate = airDate;
        this.crewList = crewList;
        this.episodeNumber = episodeNumber;
        this.starsList = starsList;
        this.name = name;
        this.overview = overview;
        this.id = id;
        this.stillPath = stillPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public String getAirDate() {
        return airDate;
    }

    public List<Crew> getCrewList() {
        return crewList;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public List<Stars> getStarsList() {
        return starsList;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public int getId() {
        return id;
    }

    public String getStillPath() {
        return stillPath;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }
}
