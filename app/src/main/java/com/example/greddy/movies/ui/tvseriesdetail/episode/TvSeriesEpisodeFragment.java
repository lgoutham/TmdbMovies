package com.example.greddy.movies.ui.tvseriesdetail.episode;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Episode;
import com.example.greddy.movies.model.SeasonResponse;

/**
 * Created by greddy on 8/3/2017.
 */

public class TvSeriesEpisodeFragment extends Fragment {

    public static final String TAG = TvSeriesEpisodeFragment.class.getSimpleName();

    public static final String SERIES_NUMBER = "SERIES_NUMBER";
    public static final String SEASON_NUMBER = "SEASON_NUMBER";
    public static final String SEASON_NAME = "SEASON_NAME";

    private ScrollView mScrollView;
    private TextView mEpisodeName;
    private TextView mReleaseDate;
    private RatingBar mRatingBar;
    private TextView mNoOfRatings;
    private TextView mOverview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.tv_series_season_episode, container, false);
        mScrollView = view.findViewById(R.id.tv_series_episode_scrollview);
        mEpisodeName = view.findViewById(R.id.tv_season_episode_name);
        mReleaseDate = view.findViewById(R.id.tv_season_episode_release_date);
        mRatingBar = view.findViewById(R.id.tv_season_episode_ratingBar);
        mNoOfRatings = view.findViewById(R.id.tv_season_episode_no_of_ratings);
        mOverview = view.findViewById(R.id.tv_season_episode_overview);
        ViewPager mEpisodeViewPager = view.findViewById(R.id.tv_series_season_viewpager);
        int pageMargin = (int) (getResources().getDimension(R.dimen.global_default_dimen_16px));
        mEpisodeViewPager.setPageMargin(pageMargin);

        int pagePadding = (int) (getResources().getDimension(R.dimen.global_default_dimen_30px));
        mEpisodeViewPager.setClipToPadding(false);
        mEpisodeViewPager.setPadding(pagePadding, 0, pagePadding, 0);

        TvSeriesEpisodeDetailsActivity activity = (TvSeriesEpisodeDetailsActivity) getActivity();
        final SeasonResponse seasonResponse = activity.getTvSeriesSeasonResponse();
        setEpisodeData(seasonResponse.getEpisodeList().get(0));
        mScrollView.fullScroll(ScrollView.FOCUS_UP);
        TvSeriesEpisodeViewPagerAdapter mTvSeriesEpisodeViewPagerAdapter = new TvSeriesEpisodeViewPagerAdapter(getActivity(), seasonResponse.getEpisodeList());
        mEpisodeViewPager.setAdapter(mTvSeriesEpisodeViewPagerAdapter);
        mEpisodeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
                setEpisodeData(seasonResponse.getEpisodeList().get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private void setEpisodeData(Episode episode) {
        mEpisodeName.setText(episode.getName());
        mReleaseDate.setText(episode.getAirDate());
        float rating = (episode.getVoteAverage() / 2);
        mRatingBar.setRating(rating);
        mNoOfRatings.setText(String.valueOf(episode.getVoteCount()).concat(" ").concat(getResources().getString(R.string.ratings)));
        mOverview.setText(episode.getOverview());
    }
}
