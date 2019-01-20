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
import com.example.greddy.movies.model.TvSeries;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.tvseriesdetail.TvSeriesDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.example.greddy.movies.home.TmdbActivity.TV_SERIES_ID;

/**
 * Created by greddy on 7/18/2017.
 */

class TvSeriesAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<TvSeries> mTvSeriesList;

    public TvSeriesAdapter(Context mContext, List<TvSeries> tvSeriesList) {
        this.mContext = mContext;
        this.mTvSeriesList = tvSeriesList;
    }

    @Override
    public int getCount() {
        return mTvSeriesList != null ? mTvSeriesList.size() : 0;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_item_movie, container, false);
        final ImageView tvSeriesPoster = view.findViewById(R.id.movie_poster);

        view.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, TvSeriesDetailsActivity.class);
            intent.putExtra(TV_SERIES_ID, mTvSeriesList.get(position).getId());
            mContext.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, tvSeriesPoster, tvSeriesPoster.getTransitionName()).toBundle());
        });

        String posterPath = ApiClient.IMAGE_W185 + mTvSeriesList.get(position).getPosterPath();
        Picasso.with(mContext).load(posterPath).into(tvSeriesPoster);

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
