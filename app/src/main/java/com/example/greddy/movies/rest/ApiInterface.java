package com.example.greddy.movies.rest;

import com.example.greddy.movies.model.GenreResponse;
import com.example.greddy.movies.model.ImageResponse;
import com.example.greddy.movies.model.Movie;
import com.example.greddy.movies.model.MovieResponse;
import com.example.greddy.movies.model.SeasonResponse;
import com.example.greddy.movies.model.TvSeriesDetail;
import com.example.greddy.movies.model.TvSeriesEpisodeImageResponse;
import com.example.greddy.movies.model.TvSeriesResponse;
import com.example.greddy.movies.model.Video;
import com.example.greddy.movies.model.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by greddy on 6/30/2017.
 */

public interface ApiInterface {

    /*MOVIES CALLS*/
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpComingMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/images")
    Call<TvSeriesResponse> getMovieImages(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<Video> getMovieVideos(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<GenreResponse> getGenreList(@Query("api_key") String apiKey);

    @GET("genre/{id}/movies")
    Call<MovieResponse> getMoviesByFilter(@Path("id") int id, @Query("api_key") String apiKey);


    /*TV SERIES CALLS*/
    @GET("tv/airing_today")
    Call<TvSeriesResponse> getAiringTodayTvSeries(@Query("api_key") String apiKey);

    @GET("tv/on_the_air")
    Call<TvSeriesResponse> getOnTheAirTvSeries(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<TvSeriesResponse> getPopularTvSeries(@Query("api_key") String apiKey);

    @GET("tv/top_rated")
    Call<TvSeriesResponse> getTopRatedTvSeries(@Query("api_key") String apiKey);

    @GET("tv/{id}")
    Call<TvSeriesDetail> getTvSeriesDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{seriesId}/season/{seasonNo}")
    Call<SeasonResponse> getTvSeriesSeason(@Path("seriesId") int seriesId, @Path("seasonNo") int seasonNo, @Query("api_key") String apiKey);

    @GET("tv/{id}/images")
    Call<ImageResponse> getTvSeriesImages(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{seriesId}/season/{seasonNo}/images")
    Call<TvSeriesEpisodeImageResponse> getTvSeriesEpisodeImages(@Path("seriesId") int seriesId, @Path("seasonNo") int seasonNo, @Query("api_key") String apiKey);

    @GET("tv/{id}/videos")
    Call<VideoResponse> getTvSeriesVideos(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("tv/{seriesId}/season/{seasonNo}/videos")
    Call<VideoResponse> getTvSeriesEpisodeVideos(@Path("seriesId") int seriesId, @Path("seasonNo") int seasonNo, @Query("api_key") String apiKey);

}
