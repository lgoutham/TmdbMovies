package com.example.greddy.movies.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by greddy on 6/30/2017.
 */

public class ApiClient {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";

    public static final String IMAGE_W185 = "https://image.tmdb.org/t/p/w185";
    public static final String IMAGE_W300 = "https://image.tmdb.org/t/p/w300";
    public static final String IMAGE_W500 = "https://image.tmdb.org/t/p/w500";
    public static final String IMAGE_W780 = "https://image.tmdb.org/t/p/w780";
    public static final String API_KEY = "YOUR_API_KEY";

    public static final String VIDEO_BASE_URL = "https://youtube.com/watch?v=";
    public static final String VIDEO_THUMBNAIL_URL = "http://i1.ytimg.com/vi/";
    public static final String VIDEO_HQ_THUMBNAIL = "/hqdefault.jpg";
    public static final String VIDEO_NORMAL_THUMBNAIL = "/0.jpg";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
