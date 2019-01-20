package com.example.greddy.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by greddy on 8/3/2017.
 */

public class Episode {

    @SerializedName("air_date")
    private final String airDate;
    @SerializedName("crew")
    private final List<Crew> crewList;
    @SerializedName("episode_number")
    private final int episodeNumber;
    @SerializedName("guest_stars")
    private final List<Stars> starsList;
    @SerializedName("name")
    private final String name;
    @SerializedName("overview")
    private final String overview;
    @SerializedName("id")
    private final int id;
    @SerializedName("still_path")
    private final String stillPath;
    @SerializedName("vote_average")
    private final Float voteAverage;
    @SerializedName("vote_count")
    private final int voteCount;

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
