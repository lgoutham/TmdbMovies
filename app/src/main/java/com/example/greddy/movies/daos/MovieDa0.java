package com.example.greddy.movies.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.greddy.movies.entities.Movie;

import java.util.List;

/**
 * Created by gautam on 14/11/17.
 */

@Dao
public interface MovieDa0 {

    @Query("SELECT * FROM user")
    List<Movie> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:movieIds)")
    List<Movie> loadAllByIds(int[] movieIds);

    @Insert
    void insertAll(Movie... users);

    @Delete
    void delete(Movie user);
}
