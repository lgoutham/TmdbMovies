package com.example.greddy.movies.di.modules;

import com.example.greddy.movies.di.modules.main.MainFragmentBuilderModule;
import com.example.greddy.movies.di.modules.main.MainModule;
import com.example.greddy.movies.di.modules.main.MainScope;
import com.example.greddy.movies.di.modules.main.MainViewModelModule;
import com.example.greddy.movies.ui.home.TmdbActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentBuilderModule.class, MainViewModelModule.class, MainModule.class}
    )
    abstract TmdbActivity contributesTmdbActivity();
}
