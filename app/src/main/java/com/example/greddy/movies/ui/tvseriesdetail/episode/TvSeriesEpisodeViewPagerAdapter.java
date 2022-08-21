package com.example.greddy.movies.ui.tvseriesdetail.episode;

import android.content.Context;

import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Episode;
import com.example.greddy.movies.rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by greddy on 8/4/2017.
 */

class TvSeriesEpisodeViewPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<Episode> mEpisodeList;

    public TvSeriesEpisodeViewPagerAdapter(Context context, List<Episode> mEpisodeList) {
        this.mContext = context;
        this.mEpisodeList = mEpisodeList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.tv_series_episode_view, container, false);
        ImageView episodePoster = view.findViewById(R.id.episode_poster);
        String posterPath = ApiClient.IMAGE_W500 + mEpisodeList.get(position).getStillPath();
        Picasso.with(mContext).load(posterPath).into(episodePoster);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mEpisodeList.size();
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
