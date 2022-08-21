package com.example.greddy.movies.di.modules.main;

import com.example.greddy.movies.network.movie.MoviesApi;
import com.example.greddy.movies.network.tvseries.TvSeriesApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static MoviesApi providesMoviesApi(Retrofit retrofit) {
        return retrofit.create(MoviesApi.class);
    }

    @MainScope
    @Provides
    static TvSeriesApi providesTvSeriesApi(Retrofit retrofit) {
        return retrofit.create(TvSeriesApi.class);
    }
}
