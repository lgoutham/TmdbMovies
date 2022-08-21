package com.example.greddy.movies.ui.tvseriesdetail.episode;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Image;
import com.example.greddy.movies.rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by greddy on 8/8/2017.
 */

class TvSeriesEpisodeImageAdpater extends PagerAdapter {

    private final Context mContext;
    private List<Image> mImages;

    public TvSeriesEpisodeImageAdpater(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_series_episode_images_item_view, container, false);
        final ImageView image = view.findViewById(R.id.tv_series_image_item);
        String posterPath = ApiClient.IMAGE_W780 + mImages.get(position).getFilePath();
        Picasso.with(mContext).load(posterPath).into(image);

        container.addView(view);
        return view;
    }

    public void updateData(List<Image> backdrops) {
        mImages = backdrops;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mImages != null ? mImages.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }
}
