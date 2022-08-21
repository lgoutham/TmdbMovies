package com.example.greddy.movies.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.greddy.movies.base.AppBaseActivity;
import com.example.greddy.movies.base.BaseViewModel;
import com.example.greddy.movies.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.MovieResponse;
import com.example.greddy.movies.ui.filter.MovieFilter;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TmdbActivity extends AppBaseActivity<ActivityMainBinding, BaseViewModel> implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = TmdbActivity.class.getSimpleName();
    private static final int SETTINGS_REQUEST_CODE = 1;
    public static final String MOVIE_ID = "MOVIE_ID";
    public static final String TV_SERIES_ID = "TV_SERIES_ID";
    public static final int DEFAULT_ID = 0;

    private MoviesAdapter mFilteredMoviesAdapter;
    private AlertDialog mDialog;
    private SharedPreferences mSharedPreferences;
    private boolean mDoubleBackToExit = false;

    @Override
    protected ActivityMainBinding getViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    protected BaseViewModel getViewModel() throws Exception {
        return new BaseViewModel();
    }

    @Override
    protected void initUi(@Nullable Bundle savedInstanceState) {
        setUpSettingsPreferences();
        binding.tmdbTabLayout.setupWithViewPager(binding.tmdbViewpager);
        binding.tmdbViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tmdbTabLayout));
        setViewPagerAdapter();
    }

    private void setViewPagerAdapter() {
        TmdbAdapter adapter = new TmdbAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieFragment());
        adapter.addFragment(new TvSeriesFragment());
        binding.tmdbViewpager.setAdapter(adapter);
    }

    private void setUpSettingsPreferences() {
        mSharedPreferences = getSharedPreferences(getString(R.string.movie_filter_shared_preferences), MODE_PRIVATE);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    private void checkApiKeyAndRequestMovies() {
        if (ApiClient.API_KEY.isEmpty()) {
            Snackbar snackbar = Snackbar.make(binding.coordinatorLayout, "Please obtain your API KEY first from themoviedb.org", Snackbar.LENGTH_LONG);
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

    private boolean isDeviceOnline() {
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
                .setNegativeButton(getResources().getString(R.string.lbl_Ok), (dialog, which) -> {
                    mDialog.dismiss();
                    finish();
                })
                .setPositiveButton(getResources().getString(R.string.lbl_settings), (dialog, which) -> {
                    Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    startActivityForResult(intent, SETTINGS_REQUEST_CODE);
                    mDialog.dismiss();
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
                binding.filteredMoviesRecyclerView.setVisibility(View.VISIBLE);
                binding.filteredMoviesRecyclerView.setLayoutManager(new GridLayoutManager(TmdbActivity.this, 3));
                mFilteredMoviesAdapter = new MoviesAdapter(TmdbActivity.this, response.body().getResults());
                binding.filteredMoviesRecyclerView.setAdapter(mFilteredMoviesAdapter);
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
        binding.filteredMoviesRecyclerView.setVisibility(View.GONE);
        this.mDoubleBackToExit = true;

        new Handler().postDelayed(() -> mDoubleBackToExit = false, 2000);
    }
}
