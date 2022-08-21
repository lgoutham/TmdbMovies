package com.example.greddy.movies.di.modules;

import com.example.greddy.movies.rest.ApiConstants;
import com.example.greddy.movies.rest.ApiKeyInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TmdbAppModule {

    @Singleton
    @Provides
    static OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().clear();
        builder.addInterceptor(new ApiKeyInterceptor());
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();
    }

    @Singleton
    @Provides
    static Retrofit providesRetrofitInstance(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
