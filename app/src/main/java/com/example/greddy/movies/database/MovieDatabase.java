package com.example.greddy.movies.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.greddy.movies.daos.MovieDa0;
import com.example.greddy.movies.entities.Movie;

/**
 * Created by gautam on 14/11/17.
 */

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDa0 movieDa0();
}
