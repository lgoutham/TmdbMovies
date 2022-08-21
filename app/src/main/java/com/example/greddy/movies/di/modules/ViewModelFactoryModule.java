package com.example.greddy.movies.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.example.greddy.movies.di.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindsFactory(ViewModelProviderFactory factory);
}
