package com.example.greddy.movies.moviedetails;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;
import androidx.appcompat.widget.Toolbar;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Genre;
import com.example.greddy.movies.model.Movie;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.greddy.movies.home.TmdbActivity.DEFAULT_ID;
import static com.example.greddy.movies.home.TmdbActivity.MOVIE_ID;

/**
 * Created by greddy on 7/3/2017.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String TAG = MovieDetailsActivity.class.getSimpleName();
    private Movie mMovie;
    private ImageView mPlayButtonCircle;
    private ImageView mPlayButton;
    private ImageView mPoster;
    private ProgressBar mProgressBar;
    private TextView mTitle;
    private TextView mReleaseDate;
    private RatingBar mRatingBar;
    private TextView mNoOfRatings;
    private TextView mMovieOverview;
    private TextView mMovieGenre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.home_generic_detail_view_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.curve));

        final ImageView poster = (ImageView) findViewById(R.id.movie_details_poster);
        mPlayButtonCircle = (ImageView) findViewById(R.id.play_button_circle);
        mPlayButton = (ImageView) findViewById(R.id.play_button);
        mProgressBar = (ProgressBar) findViewById(R.id.movie_details_progressbar);

        mPoster = (ImageView) findViewById(R.id.movie_detail_poster);
        mTitle = (TextView) findViewById(R.id.movie_detail_title);
        mReleaseDate = (TextView) findViewById(R.id.movie_detail_release_date);
        mRatingBar = (RatingBar) findViewById(R.id.movie_detail_ratingBar);
        mNoOfRatings = (TextView) findViewById(R.id.movie_detail_no_of_ratings);
        mMovieOverview = (TextView) findViewById(R.id.movie_detail_overview);
        mMovieGenre = (TextView) findViewById(R.id.movie_detail_genre);

        int id = getIntent().getIntExtra(MOVIE_ID, DEFAULT_ID);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Movie> movieDetailsCall = apiInterface.getMovieDetails(id, ApiClient.API_KEY);
        movieDetailsCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                mMovie = response.body();
                Log.d(TAG, "onResponse: " + mMovie.getId() + " Movie received.");
                String posterPath = ApiClient.IMAGE_W185 + mMovie.getPosterPath();
                Picasso.with(MovieDetailsActivity.this).load(posterPath).into(mPoster);
                String backdropPath = ApiClient.IMAGE_W780 + mMovie.getBackdropPath();
                Picasso.with(MovieDetailsActivity.this).load(backdropPath).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mProgressBar.setVisibility(View.GONE);
                        poster.setImageBitmap(bitmap);
                        setPlayButtonTintColor(bitmap);
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

                mTitle.setText(mMovie.getTitle());
                List<Genre> genreIds = mMovie.getGenreIds();
                StringBuilder genre = new StringBuilder();
                for (int i = 0; i < genreIds.size(); i++) {
                    genre.append(genreIds.get(i).getName());
                    if (i == genreIds.size() - 1)
                        genre.append("");
                    else
                        genre.append(" | ");
                }
                mMovieGenre.setText(genre.toString());
                mReleaseDate.setText(mMovie.getReleaseDate());
                float rating = (float) (mMovie.getVoteAverage() / 2);
                mRatingBar.setRating(rating);
                String voteCount = String.valueOf(mMovie.getVoteCount()).concat(" ").concat(getResources().getString(R.string.ratings));
                mNoOfRatings.setText(voteCount);
                mMovieOverview.setText(mMovie.getOverview());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setPlayButtonTintColor(Bitmap bitmap) {
        Palette palette = Palette.from(bitmap).generate();
        Palette.Swatch swatch = palette.getVibrantSwatch();
        if (swatch != null && mMovie.getVideo()) {
            mPlayButton.setVisibility(View.VISIBLE);
            mPlayButtonCircle.setVisibility(View.VISIBLE);
            mPlayButtonCircle.setColorFilter(swatch.getTitleTextColor());
        } else {
            mPlayButton.setVisibility(View.GONE);
            mPlayButtonCircle.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRatingBar != null)
            mRatingBar.setRating(0.0f);
        mMovie = null;
        mPlayButtonCircle = null;
        mPlayButton = null;
        mPoster = null;
        mProgressBar = null;
        mTitle = null;
        mReleaseDate = null;
        mRatingBar = null;
        mNoOfRatings = null;
        mMovieOverview = null;
        mMovieGenre = null;
    }
}
