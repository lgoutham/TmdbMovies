package com.example.greddy.movies.tvseriesdetail.episode;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.SeasonResponse;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.greddy.movies.tvseriesdetail.episode.TvSeriesEpisodeFragment.SEASON_NAME;
import static com.example.greddy.movies.tvseriesdetail.episode.TvSeriesEpisodeFragment.SEASON_NUMBER;
import static com.example.greddy.movies.tvseriesdetail.episode.TvSeriesEpisodeFragment.SERIES_NUMBER;

/**
 * Created by greddy on 8/8/2017.
 */

public class TvSeriesEpisodeDetailsActivity extends AppCompatActivity {

    public static final String TAG = TvSeriesEpisodeDetailsActivity.class.getSimpleName();

    private ViewPager mTvSeriesEpisodeViewPager;
    private SeasonResponse mSeasonResponse;
    private int mSeriesNo;
    private int mSeasonNo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_series_season_details_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_series_season_toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.tv_season_episode_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mSeriesNo = getIntent().getIntExtra(SERIES_NUMBER, -1);
        mSeasonNo = getIntent().getIntExtra(SEASON_NUMBER, -1);
        String seriesName = getIntent().getStringExtra(SEASON_NAME);
        getSupportActionBar().setTitle(seriesName);

        TabLayout mTvDetailsTabLayout = (TabLayout) findViewById(R.id.tv_details_tab_layout);
        mTvSeriesEpisodeViewPager = (ViewPager) findViewById(R.id.tv_details_view_pager);

        mTvDetailsTabLayout.setupWithViewPager(mTvSeriesEpisodeViewPager);
        mTvSeriesEpisodeViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTvDetailsTabLayout));

        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (mSeasonNo > -1 && mSeasonNo > -1) {
            Call<SeasonResponse> seasonResponseCall = mApiInterface.getTvSeriesSeason(mSeriesNo, mSeasonNo, ApiClient.API_KEY);
            seasonResponseCall.enqueue(new Callback<SeasonResponse>() {
                @Override
                public void onResponse(Call<SeasonResponse> call, Response<SeasonResponse> response) {
                    mSeasonResponse = response.body();
                    Log.d(TAG, "onResponse: " + mSeasonResponse.getEpisodeList().size() + " episodes received.");
                    setViewPagerAdapter();
                }

                @Override
                public void onFailure(Call<SeasonResponse> call, Throwable t) {

                }
            });
        }
    }

    private void setViewPagerAdapter() {
        TvSeriesEpisodeAdapter adapter = new TvSeriesEpisodeAdapter(getSupportFragmentManager());
        adapter.addFragment(new TvSeriesEpisodeInfoFragment());
        adapter.addFragment(new TvSeriesEpisodeFragment());
        adapter.addFragment(TvSeriesEpisodeImageFragment.newInstance(mSeriesNo, mSeasonNo));
//        adapter.addFragment(new TvSeriesVideosFragment());
        mTvSeriesEpisodeViewPager.setAdapter(adapter);
    }

    public SeasonResponse getTvSeriesSeasonResponse() {
        return mSeasonResponse;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTvSeriesEpisodeViewPager = null;
        mSeasonResponse = null;
    }
}
