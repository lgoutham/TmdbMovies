package com.example.greddy.movies.ui.home;

import static com.example.greddy.movies.ui.home.TmdbActivity.TV_SERIES_ID;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Movie;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.ui.tvseriesdetail.TvSeriesDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

class MovieAdapter extends RecyclerView.Adapter<PosterViewHolder> {

    private final Context mContext;
    private final List<Movie> mMovieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.mContext = context;
        this.mMovieList = movieList;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_movie, parent, false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        String posterPath = ApiClient.IMAGE_W185 + mMovieList.get(position).getPosterPath();
        Picasso.with(mContext).load(posterPath).into(holder.poster);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, TvSeriesDetailsActivity.class);
            intent.putExtra(TV_SERIES_ID, mMovieList.get(position).getId());
            mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, holder.poster, holder.poster.getTransitionName()).toBundle());
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList != null ? mMovieList.size() : 0;
    }
}
