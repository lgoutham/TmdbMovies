package com.example.greddy.movies.network.tvseries;

import com.example.greddy.movies.model.TvSeriesResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TvSeriesRepository {

    private TvSeriesApi tvSeriesApi;

    @Inject
    public TvSeriesRepository(TvSeriesApi tvSeriesApi) {
        this.tvSeriesApi = tvSeriesApi;
    }

    public Observable<TvSeriesResponse> getPopularTvSeries() {
        return tvSeriesApi.getPopularTvSeries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TvSeriesResponse> getOnTheAirTvSeries() {
        return tvSeriesApi.getOnTheAirTvSeries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TvSeriesResponse> getAiringTodayTvSeries() {
        return tvSeriesApi.getAiringTodayTvSeries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TvSeriesResponse> getTopRatedTvSeries() {
        return tvSeriesApi.getTopRatedTvSeries().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
