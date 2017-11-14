package com.example.greddy.movies.database;

/**
 * Created by gautam on 1/11/17.
 */

public class TablesData {

    //Table Names
    public static final String MOVIE_TABLE = "movie";

    //movie Table column names
    public static final String MOVIE_ID = "id";
    public static final String TITLE = "title";
    public static final String RELEASE_DATE = "releaseDate";
    public static final String OVERVIEW = "overview";
    public static final String VOTE_COUNT = "vote_count";
    public static final String VOTE_AVG = "vote_average";
    public static final String POPULARITY = "popularity";

    //movie Table create statement
    public static final String CREATE_TABLE_MOVIE = "CREATE TABLE " + MOVIE_TABLE +
            "(" + MOVIE_ID + " INTEGER PRIMARY KEY,"
            + TITLE + " TEXT,"
            + RELEASE_DATE + " TEXT,"
            + OVERVIEW + " TEXT,"
            + VOTE_COUNT + " DOUBLE,"
            + VOTE_AVG + " DOUBLE,"
            + POPULARITY + " DOUBLE" + ")";
}
