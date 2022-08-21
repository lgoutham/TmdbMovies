package com.example.greddy.movies.di.components;

import android.app.Application;

import com.example.greddy.movies.TmdbApplication;
import com.example.greddy.movies.di.modules.ActivityBuilderModule;
import com.example.greddy.movies.di.modules.TmdbAppModule;
import com.example.greddy.movies.di.modules.ViewModelFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        TmdbAppModule.class,
        ActivityBuilderModule.class,
        ViewModelFactoryModule.class
})
public interface TmdbComponent extends AndroidInjector<TmdbApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        TmdbComponent build();
    }
}
