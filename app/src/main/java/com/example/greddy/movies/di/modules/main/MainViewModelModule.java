package com.example.greddy.movies.di.modules.main;

import androidx.lifecycle.ViewModel;

import com.example.greddy.movies.di.ViewModelKey;
import com.example.greddy.movies.ui.home.MovieViewModel;
import com.example.greddy.movies.ui.home.TvSeriesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel.class)
    abstract ViewModel bindsMovieViewModel(MovieViewModel movieViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TvSeriesViewModel.class)
    abstract ViewModel bindsTvSeriesViewModel(TvSeriesViewModel tvSeriesViewModel);
}
