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
import com.example.greddy.movies.model.Movie;
import com.example.greddy.movies.model.MovieResponse;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by greddy on 7/18/2017.
 */

public class MovieFragment extends Fragment {

    public static final String TAG = MovieFragment.class.getSimpleName();

    private ApiInterface mApiInterface;
    private Context mContext;

    private ViewPager mNowPlayingViewPager;
    private ViewPager mPopularViewPager;
    private ViewPager mTopRatedViewPager;
    private ViewPager mUpcomingViewPager;
    private MovieAdapter mMovieAdapter;

    private List<Movie> mNowPlayingMovies;
    private List<Movie> mPopularMovies;
    private List<Movie> mTopRatedMovies;
    private List<Movie> mUpcomingMovies;

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

        CardView mNowPlayingMoviesCardView = (CardView) view.findViewById(R.id.now_playing_movies_card_view);
        TextView mNowPlayingMoviesTextView = (TextView) mNowPlayingMoviesCardView.findViewById(R.id.card_view_movies_title);
        mNowPlayingMoviesTextView.setText(getResources().getString(R.string.now_playing));
        mNowPlayingViewPager = (ViewPager) mNowPlayingMoviesCardView.findViewById(R.id.card_view_movies_viewpager);
        mNowPlayingViewPager.setPageMargin(pageMargin);

        CardView mPopularMoviesCardView = (CardView) view.findViewById(R.id.most_popular_movies_card_view);
        TextView mPopularMoviesTextView = (TextView) mPopularMoviesCardView.findViewById(R.id.card_view_movies_title);
        mPopularMoviesTextView.setText(getResources().getString(R.string.most_popular));
        mPopularViewPager = (ViewPager) mPopularMoviesCardView.findViewById(R.id.card_view_movies_viewpager);
        mPopularViewPager.setPageMargin(pageMargin);

        CardView mTopRatedMoviesCardView = (CardView) view.findViewById(R.id.top_rated_movies_card_view);
        TextView mTopRatedMoviesTextView = (TextView) mTopRatedMoviesCardView.findViewById(R.id.card_view_movies_title);
        mTopRatedMoviesTextView.setText(getResources().getString(R.string.top_rated));
        mTopRatedViewPager = (ViewPager) mTopRatedMoviesCardView.findViewById(R.id.card_view_movies_viewpager);
        mTopRatedViewPager.setPageMargin(pageMargin);

        CardView mUpcomingMoviesCardView = (CardView) view.findViewById(R.id.upcoming_movies_card_view);
        TextView mUpcomingMoviesTextView = (TextView) mUpcomingMoviesCardView.findViewById(R.id.card_view_movies_title);
        mUpcomingMoviesTextView.setText(getResources().getString(R.string.upcoming));
        mUpcomingViewPager = (ViewPager) mUpcomingMoviesCardView.findViewById(R.id.card_view_movies_viewpager);
        mUpcomingViewPager.setPageMargin(pageMargin);
    }

    private void loadMovies() {
        Call<MovieResponse> nowPlayingMoviesResponseCall = mApiInterface.getNowPlayingMovies(ApiClient.API_KEY);
        nowPlayingMoviesResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mNowPlayingMovies = response.body().getResults();
                mMovieAdapter = new MovieAdapter(mContext, mNowPlayingMovies);
                mNowPlayingViewPager.setAdapter(mMovieAdapter);
                Log.d(TAG, "onResponse: " + mNowPlayingMovies.size() + " Movies received.");
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        Call<MovieResponse> popularMoviesResponseCall = mApiInterface.getPopularMovies(ApiClient.API_KEY);
        popularMoviesResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mPopularMovies = response.body().getResults();
                mMovieAdapter = new MovieAdapter(mContext, mPopularMovies);
                mPopularViewPager.setAdapter(mMovieAdapter);
                Log.d(TAG, "onResponse: " + mPopularMovies.size() + " Movies received.");
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        Call<MovieResponse> topRatedMoviesResponseCall = mApiInterface.getTopRatedMovies(ApiClient.API_KEY);
        topRatedMoviesResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mTopRatedMovies = response.body().getResults();
                mMovieAdapter = new MovieAdapter(mContext, mTopRatedMovies);
                mTopRatedViewPager.setAdapter(mMovieAdapter);
                Log.d(TAG, "onResponse: " + mTopRatedMovies.size() + " Movies received.");
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        Call<MovieResponse> upcomingMoviesResponseCall = mApiInterface.getUpComingMovies(ApiClient.API_KEY);
        upcomingMoviesResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mUpcomingMovies = response.body().getResults();
                mMovieAdapter = new MovieAdapter(mContext, mUpcomingMovies);
                mUpcomingViewPager.setAdapter(mMovieAdapter);
                Log.d(TAG, "onResponse: " + mUpcomingMovies.size() + " Movies received.");
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
