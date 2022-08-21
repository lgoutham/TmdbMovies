package com.example.greddy.movies.network.tvseries;

import com.example.greddy.movies.model.TvSeriesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TvSeriesApi {

    @GET("tv/airing_today")
    Observable<TvSeriesResponse> getAiringTodayTvSeries();

    @GET("tv/on_the_air")
    Observable<TvSeriesResponse> getOnTheAirTvSeries();

    @GET("tv/popular")
    Observable<TvSeriesResponse> getPopularTvSeries();

    @GET("tv/top_rated")
    Observable<TvSeriesResponse> getTopRatedTvSeries();
}
