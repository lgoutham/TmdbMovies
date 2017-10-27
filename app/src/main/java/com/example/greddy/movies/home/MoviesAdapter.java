package com.example.greddy.movies.home;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Movie;
import com.example.greddy.movies.moviedetails.MovieDetailsActivity;
import com.example.greddy.movies.rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.greddy.movies.home.TmdbActivity.MOVIE_ID;

/**
 * Created by greddy on 6/30/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private Context mContext;

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
        holder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra(MOVIE_ID, movies.get(position).getId());
                mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.moviePoster, holder.moviePoster.getTransitionName()).toBundle());
            }
        });
        String posterPath = ApiClient.IMAGE_W185 + movies.get(position).getPosterPath();
        Picasso.with(mContext).load(posterPath).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePoster;

        public MovieViewHolder(View v) {
            super(v);
            moviePoster = v.findViewById(R.id.movie_poster);
        }
    }
}
