package com.example.greddy.movies.home;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.TvSeries;
import com.example.greddy.movies.model.TvSeriesResponse;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by greddy on 7/18/2017.
 */

public class TvSeriesFragment extends Fragment {

    private static final String TAG = TvSeriesFragment.class.getSimpleName();

    private ApiInterface mApiInterface;
    private Context mContext;

    private ViewPager mAiringTodayViewPager;
    private ViewPager mOnAirViewPager;
    private ViewPager mPopularViewPager;
    private ViewPager mTopRatedViewPager;
    private TvSeriesAdapter mMovieAdapter;

    private List<TvSeries> mAiringTodayTvSeries;
    private List<TvSeries> mOnAirTvSeries;
    private List<TvSeries> mPopularTvSeries;
    private List<TvSeries> mTopRatedTvSeries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_main_content, container, false);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        loadView(view);
        loadMovies();
        return view;
    }

    private void loadView(View view) {
        int pageMargin = (int) (getResources().getDimension(R.dimen.global_default_dimen_12px));

        CardView airingTodayCardView = view.findViewById(R.id.now_playing_movies_card_view);
        TextView airingTodayTextView = airingTodayCardView.findViewById(R.id.card_view_movies_title);
        airingTodayTextView.setText(getResources().getString(R.string.airing_today));
        mAiringTodayViewPager = airingTodayCardView.findViewById(R.id.card_view_movies_viewpager);
        mAiringTodayViewPager.setPageMargin(pageMargin);

        CardView mOnAirCardView = view.findViewById(R.id.most_popular_movies_card_view);
        TextView mOnAirTextView = mOnAirCardView.findViewById(R.id.card_view_movies_title);
        mOnAirTextView.setText(getResources().getString(R.string.on_air));
        mOnAirViewPager = mOnAirCardView.findViewById(R.id.card_view_movies_viewpager);
        mOnAirViewPager.setPageMargin(pageMargin);

        CardView mPopularCardView = view.findViewById(R.id.top_rated_movies_card_view);
        TextView mPopularTextView = mPopularCardView.findViewById(R.id.card_view_movies_title);
        mPopularTextView.setText(getResources().getString(R.string.most_popular));
        mPopularViewPager = mPopularCardView.findViewById(R.id.card_view_movies_viewpager);
        mPopularViewPager.setPageMargin(pageMargin);

        CardView mTopRatedCardView = view.findViewById(R.id.upcoming_movies_card_view);
        TextView mTopRatedTextView = mTopRatedCardView.findViewById(R.id.card_view_movies_title);
        mTopRatedTextView.setText(getResources().getString(R.string.top_rated));
        mTopRatedViewPager = mTopRatedCardView.findViewById(R.id.card_view_movies_viewpager);
        mTopRatedViewPager.setPageMargin(pageMargin);
    }

    private void loadMovies() {
        Call<TvSeriesResponse> airingTodayTvSeriesResponseCall = mApiInterface.getAiringTodayTvSeries(ApiClient.API_KEY);
        airingTodayTvSeriesResponseCall.enqueue(new Callback<TvSeriesResponse>() {
            @Override
            public void onResponse(Call<TvSeriesResponse> call, Response<TvSeriesResponse> response) {
                mAiringTodayTvSeries = response.body().getResults();
                mMovieAdapter = new TvSeriesAdapter(mContext, mAiringTodayTvSeries);
                mAiringTodayViewPager.setAdapter(mMovieAdapter);
                Log.d(TAG, "onResponse: " + mAiringTodayTvSeries.size() + " Movies received.");
            }

            @Override
            public void onFailure(Call<TvSeriesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        Call<TvSeriesResponse> onTheAirTvSeriesResponseCall = mApiInterface.getOnTheAirTvSeries(ApiClient.API_KEY);
        onTheAirTvSeriesResponseCall.enqueue(new Callback<TvSeriesResponse>() {
            @Override
            public void onResponse(Call<TvSeriesResponse> call, Response<TvSeriesResponse> response) {
                mOnAirTvSeries = response.body().getResults();
                mMovieAdapter = new TvSeriesAdapter(mContext, mOnAirTvSeries);
                mOnAirViewPager.setAdapter(mMovieAdapter);
                Log.d(TAG, "onResponse: " + mOnAirTvSeries.size() + " Movies received.");
            }

            @Override
            public void onFailure(Call<TvSeriesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        Call<TvSeriesResponse> popularTvSeriesResponseCall = mApiInterface.getPopularTvSeries(ApiClient.API_KEY);
        popularTvSeriesResponseCall.enqueue(new Callback<TvSeriesResponse>() {
            @Override
            public void onResponse(Call<TvSeriesResponse> call, Response<TvSeriesResponse> response) {
                mPopularTvSeries = response.body().getResults();
                mMovieAdapter = new TvSeriesAdapter(mContext, mPopularTvSeries);
                mPopularViewPager.setAdapter(mMovieAdapter);
                Log.d(TAG, "onResponse: " + mPopularTvSeries.size() + " Movies received.");
            }

            @Override
            public void onFailure(Call<TvSeriesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        Call<TvSeriesResponse> topRatedTvSeriesResponseCall = mApiInterface.getTopRatedTvSeries(ApiClient.API_KEY);
        topRatedTvSeriesResponseCall.enqueue(new Callback<TvSeriesResponse>() {
            @Override
            public void onResponse(Call<TvSeriesResponse> call, Response<TvSeriesResponse> response) {
                mTopRatedTvSeries = response.body().getResults();
                mMovieAdapter = new TvSeriesAdapter(mContext, mTopRatedTvSeries);
                mTopRatedViewPager.setAdapter(mMovieAdapter);
                Log.d(TAG, "onResponse: " + mTopRatedTvSeries.size() + " Movies received.");
            }

            @Override
            public void onFailure(Call<TvSeriesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
