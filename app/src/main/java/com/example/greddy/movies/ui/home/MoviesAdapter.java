package com.example.greddy.movies.ui.home;

import static com.example.greddy.movies.ui.home.TmdbActivity.MOVIE_ID;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Movie;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.ui.moviedetails.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by greddy on 6/30/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private final List<Movie> movies;
    private final Context mContext;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.mContext = context;
        this.movies = movies;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.moviePoster.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, MovieDetailsActivity.class);
            intent.putExtra(MOVIE_ID, movies.get(position).getId());
            mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.moviePoster, holder.moviePoster.getTransitionName()).toBundle());
        });
        String posterPath = ApiClient.IMAGE_W185 + movies.get(position).getPosterPath();
        Picasso.with(mContext).load(posterPath).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        final ImageView moviePoster;

        MovieViewHolder(View v) {
            super(v);
            moviePoster = v.findViewById(R.id.movie_poster);
        }
    }
}
