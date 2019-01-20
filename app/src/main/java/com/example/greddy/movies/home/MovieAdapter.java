package com.example.greddy.movies.home;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.moviedetails.MovieDetailsActivity;
import com.example.greddy.movies.model.Movie;
import com.example.greddy.movies.rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.greddy.movies.home.TmdbActivity.MOVIE_ID;

/**
 * Created by greddy on 7/10/2017.
 */

public class MovieAdapter extends PagerAdapter {

    private Context mContext;
    private List<Movie> mMovieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.mContext = context;
        this.mMovieList = movieList;
    }

    @Override
    public int getCount() {
        return mMovieList != null ? mMovieList.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_movie, container, false);
        final ImageView moviePoster = (ImageView) view.findViewById(R.id.movie_poster);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra(MOVIE_ID, mMovieList.get(position).getId());
                mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, moviePoster, moviePoster.getTransitionName()).toBundle());
            }
        });

        String posterPath = ApiClient.IMAGE_W185 + mMovieList.get(position).getPosterPath();
        Picasso.with(mContext).load(posterPath).into(moviePoster);

        container.addView(view);
        return view;
    }

    @Override
    public float getPageWidth(int position) {
        return 1f / 3f;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
