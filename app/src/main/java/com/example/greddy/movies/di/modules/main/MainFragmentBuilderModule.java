package com.example.greddy.movies.di.modules.main;

import com.example.greddy.movies.ui.home.MovieFragment;
import com.example.greddy.movies.ui.home.TvSeriesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MovieFragment contributesMovieFragment();

    @ContributesAndroidInjector
    abstract TvSeriesFragment contributesTvSeriesFragment();
}
