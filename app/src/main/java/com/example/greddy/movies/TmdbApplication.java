package com.example.greddy.movies;


import com.example.greddy.movies.di.components.DaggerTmdbComponent;
import com.example.greddy.movies.di.components.TmdbComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class TmdbApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        TmdbComponent tmdbComponent = DaggerTmdbComponent.builder().application(this).build();
        tmdbComponent.inject(this);
        return tmdbComponent;
    }
}
