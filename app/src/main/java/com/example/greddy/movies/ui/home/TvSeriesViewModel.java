package com.example.greddy.movies.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.greddy.movies.base.BaseViewModel;
import com.example.greddy.movies.model.TvSeriesResponse;
import com.example.greddy.movies.network.tvseries.TvSeriesRepository;

import javax.inject.Inject;

public class TvSeriesViewModel extends BaseViewModel {

    private final TvSeriesRepository repository;
    private final MutableLiveData<TvSeriesResponse> onTheAirTvSeriesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<TvSeriesResponse> popularTvSeriesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<TvSeriesResponse> topRatedTvSeriesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<TvSeriesResponse> airingTodayTvSeriesMutableLiveData = new MutableLiveData<>();

    @Inject
    public TvSeriesViewModel(TvSeriesRepository tvSeriesRepository) {
        repository = tvSeriesRepository;
    }

    public LiveData<TvSeriesResponse> getOnTheAirTvSeriesMutableLiveData() {
        return onTheAirTvSeriesMutableLiveData;
    }

    public LiveData<TvSeriesResponse> getPopularTvSeriesMutableLiveData() {
        return popularTvSeriesMutableLiveData;
    }

    public LiveData<TvSeriesResponse> getTopRatedTvSeriesMutableLiveData() {
        return topRatedTvSeriesMutableLiveData;
    }

    public LiveData<TvSeriesResponse> getAiringTodayTvSeriesMutableLiveData() {
        return airingTodayTvSeriesMutableLiveData;
    }

    public void getOnTheAirTvSeries() {
        disposables.add(repository.getOnTheAirTvSeries().subscribe(onTheAirTvSeriesMutableLiveData::postValue, throwable -> {
            onTheAirTvSeriesMutableLiveData.postValue(null);
        }));
    }

    public void getAiringTodayTvSeries() {
        disposables.add(repository.getAiringTodayTvSeries().subscribe(airingTodayTvSeriesMutableLiveData::postValue, throwable -> {
            airingTodayTvSeriesMutableLiveData.postValue(null);
        }));
    }

    public void getPopularTvSeries() {
        disposables.add(repository.getPopularTvSeries().subscribe(popularTvSeriesMutableLiveData::postValue, throwable -> {
            popularTvSeriesMutableLiveData.postValue(null);
        }));
    }

    public void getTopRatedTvSeries() {
        disposables.add(repository.getTopRatedTvSeries().subscribe(topRatedTvSeriesMutableLiveData::postValue, throwable -> {
            topRatedTvSeriesMutableLiveData.postValue(null);
        }));
    }
}
