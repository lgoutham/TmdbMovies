package com.example.greddy.movies.tvseriesdetail;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.TvSeriesDetail;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;
import com.example.greddy.movies.tvseriesdetail.season.TvSeriesInfoFragment;
import com.example.greddy.movies.tvseriesdetail.season.TvSeriesSeasonsFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.greddy.movies.home.TmdbActivity.DEFAULT_ID;
import static com.example.greddy.movies.home.TmdbActivity.TV_SERIES_ID;

/**
 * Created by greddy on 7/18/2017.
 */

public class TvSeriesDetailsActivity extends AppCompatActivity {

    public static final String TAG = TvSeriesDetailsActivity.class.getSimpleName();
    private TvSeriesDetail mTvSeries;
    private ProgressBar mProgressBar;

    private Toolbar mToolbar;
    private ViewPager mTvDetailsViewPager;

    private Call<TvSeriesDetail> mMovieDetailsCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_series_details_activity);

        mToolbar = (Toolbar) findViewById(R.id.home_generic_detail_view_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.curve));
        final ImageView poster = (ImageView) findViewById(R.id.tv_series_details_poster);
        mProgressBar = (ProgressBar) findViewById(R.id.tv_series_details_progressbar);

        TabLayout mTvDetailsTabLayout = (TabLayout) findViewById(R.id.tv_details_tab_layout);
        mTvDetailsViewPager = (ViewPager) findViewById(R.id.tv_details_view_pager);

        mTvDetailsTabLayout.setupWithViewPager(mTvDetailsViewPager);
        mTvDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTvDetailsTabLayout));

        int id = getIntent().getIntExtra(TV_SERIES_ID, DEFAULT_ID);

        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        mMovieDetailsCall = mApiInterface.getTvSeriesDetails(id, ApiClient.API_KEY);
        mMovieDetailsCall.enqueue(new Callback<TvSeriesDetail>() {
            @Override
            public void onResponse(Call<TvSeriesDetail> call, Response<TvSeriesDetail> response) {
                mTvSeries = response.body();
                Log.d(TAG, "onResponse: " + mTvSeries.getId() + " Movie received.");
                String backdropPath = ApiClient.IMAGE_W780 + mTvSeries.getBackdropPath();
                Picasso.with(TvSeriesDetailsActivity.this).load(backdropPath).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mProgressBar.setVisibility(View.GONE);
                        poster.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {
                        poster.setImageDrawable(errorDrawable);
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        poster.setImageDrawable(placeHolderDrawable);
                    }
                });

                mToolbar.setTitle(mTvSeries.getName());
                setViewPagerAdapter();
            }

            @Override
            public void onFailure(Call<TvSeriesDetail> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setViewPagerAdapter() {
        TvSeriesDetailViewAdapter adapter = new TvSeriesDetailViewAdapter(getSupportFragmentManager());
        adapter.addFragment(new TvSeriesInfoFragment());
        adapter.addFragment(new TvSeriesSeasonsFragment());
        adapter.addFragment(new TvSeriesImagesFragment());
        adapter.addFragment(new TvSeriesVideosFragment());
        mTvDetailsViewPager.setAdapter(adapter);
    }

    public TvSeriesDetail getTvSeriesDetails(){
        return mTvSeries;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMovieDetailsCall != null)
            mTvSeries = null;
        mProgressBar = null;
    }
}
