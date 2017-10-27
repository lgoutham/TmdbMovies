package com.example.greddy.movies.tvseriesdetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by greddy on 8/7/2017.
 */

public class TvSeriesImagesAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Image> mImages;

    public TvSeriesImagesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TvSeriesImageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.tv_series_images_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String posterPath = ApiClient.IMAGE_W780 + mImages.get(position).getFilePath();
        Picasso.with(mContext).load(posterPath).into(((TvSeriesImageViewHolder) holder).poster);
    }

    @Override
    public int getItemCount() {
        return mImages != null ? mImages.size() : 0;
    }

    public void updateData(List<Image> backdrops) {
        mImages = backdrops;
        this.notifyDataSetChanged();
    }

    public class TvSeriesImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;

        public TvSeriesImageViewHolder(View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.tv_series_image_item);
        }
    }
}
