package com.example.greddy.movies.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.greddy.movies.base.BaseViewModel;
import com.example.greddy.movies.model.MovieResponse;
import com.example.greddy.movies.network.movie.MovieRepository;

import javax.inject.Inject;

public class MovieViewModel extends BaseViewModel {

    private final MovieRepository movieRepository;
    private final MutableLiveData<MovieResponse> nowPlayingMoviesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<MovieResponse> popularMoviesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<MovieResponse> topRatedMoviesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<MovieResponse> upcomingMoviesMutableLiveData = new MutableLiveData<>();

    @Inject
    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<MovieResponse> getPopularMoviesMutableLiveData() {
        return popularMoviesMutableLiveData;
    }

    public LiveData<MovieResponse> getNowPlayingMoviesMutableLiveData() {
        return nowPlayingMoviesMutableLiveData;
    }

    public LiveData<MovieResponse> getTopRatedMoviesMutableLiveData() {
        return topRatedMoviesMutableLiveData;
    }

    public LiveData<MovieResponse> getUpcomingMoviesMutableLiveData() {
        return upcomingMoviesMutableLiveData;
    }

    public void getPopularMovies() {
        disposables.add(movieRepository.getPopularMovies().subscribe(
                popularMoviesMutableLiveData::postValue,
                throwable -> {
                    popularMoviesMutableLiveData.postValue(null);
                }));
    }

    public void getNowPlayingMovies() {
        disposables.add(movieRepository.getNowPlayingMovies().subscribe(
                nowPlayingMoviesMutableLiveData::postValue,
                throwable -> {
                    nowPlayingMoviesMutableLiveData.postValue(null);
                }));
    }

    public void getTopRatedMovies() {
        disposables.add(movieRepository.getTopRatedMovies().subscribe(
                topRatedMoviesMutableLiveData::postValue,
                throwable -> {
                    topRatedMoviesMutableLiveData.postValue(null);
                }));
    }

    public void getUpComingMovies() {
        disposables.add(movieRepository.getUpComingMovies().subscribe(
                upcomingMoviesMutableLiveData::postValue,
                throwable -> {
                    upcomingMoviesMutableLiveData.postValue(null);
                }));
    }
}
