package com.example.greddy.movies.rest;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();
        HttpUrl httpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", ApiConstants.API_KEY)
                .build();
        Request.Builder builder = originalRequest.newBuilder()
                .url(httpUrl);
        Request request = builder.build();
        return chain.proceed(request);
    }
}
