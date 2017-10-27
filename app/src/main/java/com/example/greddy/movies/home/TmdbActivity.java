package com.example.greddy.movies.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.MovieResponse;
import com.example.greddy.movies.filter.MovieFilter;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TmdbActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static final String TAG = TmdbActivity.class.getSimpleName();
    public static final int SETTINGS_REQUEST_CODE = 1;
    public static final String MOVIE_ID = "MOVIE_ID";
    public static final String TV_SERIES_ID = "TV_SERIES_ID";
    public static final int DEFAULT_ID = 0;

    private CoordinatorLayout mCoordinatorLayout;

    private RecyclerView mFilteredMovies;
    private MoviesAdapter mFilteredMoviesAdapter;

    private ViewPager mTmdbViewPager;

    private MovieFilter mMovieFilterDialog;
    private AlertDialog mDialog;
    private SharedPreferences mSharedPreferences;
    private boolean mDoubleBackToExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpSettingsPreferences();
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mFilteredMovies = (RecyclerView) findViewById(R.id.filtered_movies_recycler_view);

        TabLayout mTmdbTabLayout = (TabLayout) findViewById(R.id.tmdb_tab_layout);
        mTmdbViewPager = (ViewPager) findViewById(R.id.tmdb_viewpager);
        setViewPagerAdapter();
        mTmdbTabLayout.setupWithViewPager(mTmdbViewPager);
        mTmdbViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTmdbTabLayout));

        FloatingActionButton mMoviesFilter = (FloatingActionButton) findViewById(R.id.movies_filter);
        mMoviesFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMovieFilterDialog = new MovieFilter();
                mMovieFilterDialog.show(getSupportFragmentManager(), "MovieFilter");
            }
        });
    }

    private void setViewPagerAdapter() {
        TmdbAdapter adapter = new TmdbAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieFragment());
        adapter.addFragment(new TvSeriesFragment());
        mTmdbViewPager.setAdapter(adapter);
    }

    private void setUpSettingsPreferences() {
        mSharedPreferences = getSharedPreferences(getString(R.string.movie_filter_shared_preferences), MODE_PRIVATE);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void checkApiKeyAndRequestMovies() {
        if (ApiClient.API_KEY.isEmpty()) {
            Snackbar snackbar = Snackbar.make(mCoordinatorLayout, "Please obtain your API KEY first from themoviedb.org", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else
            requestMovies();
    }

    private void requestMovies() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isDeviceOnline())
            checkApiKeyAndRequestMovies();
        else
            ShowNoNetworkDialog();
    }

    public boolean isDeviceOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isDeviceOnline = networkInfo != null && networkInfo.isConnectedOrConnecting();
        if (isDeviceOnline) {
            NetworkInfo[] networks = connectivityManager.getAllNetworkInfo();
            for (NetworkInfo n : networks) {
                if (n.getState() == NetworkInfo.State.CONNECTED || n.getState() == NetworkInfo.State.CONNECTING) {
                    if (n.getType() == ConnectivityManager.TYPE_MOBILE || n.getType() == ConnectivityManager.TYPE_WIFI) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void ShowNoNetworkDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getResources().getString(R.string.no_network_title));
        alertDialogBuilder.setMessage(getResources().getString(R.string.no_network_message))
                .setNegativeButton(getResources().getString(R.string.lbl_Ok), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                        finish();
                    }
                })
                .setPositiveButton(getResources().getString(R.string.lbl_settings), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                        startActivityForResult(intent, SETTINGS_REQUEST_CODE);
                        mDialog.dismiss();
                    }
                })
                .setCancelable(false);
        mDialog = alertDialogBuilder.create();
        mDialog.show();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        int rating = mSharedPreferences.getInt(getString(R.string.movie_filter_rating), 0);
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> upcomingMoviesResponseCall = mApiInterface.getMoviesByFilter(35, ApiClient.API_KEY);
        upcomingMoviesResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mFilteredMovies.setVisibility(View.VISIBLE);
                mFilteredMovies.setLayoutManager(new GridLayoutManager(TmdbActivity.this, 3));
                mFilteredMoviesAdapter = new MoviesAdapter(TmdbActivity.this, response.body().getResults());
                mFilteredMovies.setAdapter(mFilteredMoviesAdapter);
                Log.d(TAG, "onSharedPreferenceChanged: " + response.body().getResults().size() + " Movies received.");
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onSharedPreferenceChanged onFailure: " + t.getMessage());
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mDoubleBackToExit) {
            super.onBackPressed();
            return;
        }
        mFilteredMovies.setVisibility(View.GONE);
        this.mDoubleBackToExit = true;

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mDoubleBackToExit = false;
            }
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCoordinatorLayout = null;
        mFilteredMovies = null;
        mFilteredMoviesAdapter = null;
        mTmdbViewPager = null;
        mMovieFilterDialog = null;
        mDialog = null;
    }
}
