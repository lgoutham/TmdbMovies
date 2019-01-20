package com.example.greddy.movies.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.greddy.movies.database.DatabaseHelper;
import com.example.greddy.movies.model.Movie;

import static com.example.greddy.movies.database.TablesData.MOVIE_ID;
import static com.example.greddy.movies.database.TablesData.MOVIE_TABLE;
import static com.example.greddy.movies.database.TablesData.OVERVIEW;
import static com.example.greddy.movies.database.TablesData.POPULARITY;
import static com.example.greddy.movies.database.TablesData.RELEASE_DATE;
import static com.example.greddy.movies.database.TablesData.TITLE;
import static com.example.greddy.movies.database.TablesData.VOTE_AVG;
import static com.example.greddy.movies.database.TablesData.VOTE_COUNT;

/**
 * Created by gautam on 1/11/17.
 */

class DbUtils {

    public static long addMovie(Movie movie, Context context){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        SQLiteDatabase mSqLiteDatabase = databaseHelper.getDbWithWriteAccess();

        ContentValues values = new ContentValues();
        values.put(MOVIE_ID,movie.getId());
        values.put(TITLE,movie.getTitle());
        values.put(RELEASE_DATE,movie.getReleaseDate());
        values.put(OVERVIEW, movie.getOverview());
        values.put(VOTE_COUNT, movie.getVoteCount());
        values.put(VOTE_AVG, movie.getVoteAverage());
        values.put(POPULARITY, movie.getPopularity());

        return mSqLiteDatabase.insert(MOVIE_TABLE, null, values);
    }
}
