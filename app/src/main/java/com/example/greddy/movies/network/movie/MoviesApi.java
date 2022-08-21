package com.example.greddy.movies.network.movie;

import com.example.greddy.movies.model.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MoviesApi {

    @GET("movie/popular")
    Observable<MovieResponse> getPopularMovies();

    @GET("movie/top_rated")
    Observable<MovieResponse> getTopRatedMovies();

    @GET("movie/now_playing")
    Observable<MovieResponse> getNowPlayingMovies();

    @GET("movie/upcoming")
    Observable<MovieResponse> getUpComingMovies();

}
