package com.example.greddy.movies.network.movie;

import com.example.greddy.movies.model.MovieResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository {

    private final MoviesApi moviesApi;

    @Inject
    public MovieRepository(MoviesApi moviesApi) {
        this.moviesApi = moviesApi;
    }

    public Observable<MovieResponse> getPopularMovies() {
        return moviesApi.getPopularMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MovieResponse> getNowPlayingMovies() {
        return moviesApi.getNowPlayingMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MovieResponse> getTopRatedMovies() {
        return moviesApi.getTopRatedMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MovieResponse> getUpComingMovies() {
        return moviesApi.getUpComingMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
