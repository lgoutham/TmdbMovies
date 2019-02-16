package com.example.greddy.movies.tvseriesdetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Video;
import com.example.greddy.movies.rest.ApiClient;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by greddy on 8/7/2017.
 */

class TvSeriesVideosAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<Video> mVideoList;
    private TvSeriesVideoViewHolder mTvSeriesVideoViewHolder;

    public TvSeriesVideosAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void updateData(List<Video> videoList) {
        mVideoList = videoList;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mTvSeriesVideoViewHolder = new TvSeriesVideoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.tv_series_video_item, parent, false));
        return mTvSeriesVideoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TvSeriesVideoViewHolder viewHolder = (TvSeriesVideoViewHolder) holder;
        final String videoPath = ApiClient.VIDEO_BASE_URL + mVideoList.get(position).getVideoKey();
        String thumbnailPath = ApiClient.VIDEO_THUMBNAIL_URL + mVideoList.get(position).getVideoKey() + ApiClient.VIDEO_HQ_THUMBNAIL;
        Picasso.with(mContext).load(thumbnailPath).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                viewHolder.mThumbnail.setImageBitmap(bitmap);
                setPlayButtonTintColor(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                viewHolder.mThumbnail.setImageDrawable(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                viewHolder.mThumbnail.setImageDrawable(placeHolderDrawable);
            }
        });
        viewHolder.mThumbnail.setOnClickListener(view -> mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(videoPath))));
    }

    @Override
    public int getItemCount() {
        return mVideoList != null ? mVideoList.size() : 0;
    }

    class TvSeriesVideoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mThumbnail;
        private final ImageView mPlayButtonCircle;
        private final ImageView mPlayButton;

        TvSeriesVideoViewHolder(View itemView) {
            super(itemView);
            mThumbnail = itemView.findViewById(R.id.tv_series_video_thumbnail);
            mPlayButton = itemView.findViewById(R.id.play_button);
            mPlayButtonCircle = itemView.findViewById(R.id.play_button_circle);
        }
    }

    private void setPlayButtonTintColor(Bitmap bitmap) {
        Palette palette = Palette.from(bitmap).generate();
        Palette.Swatch swatch = palette.getDominantSwatch();
        if (swatch != null) {
            mTvSeriesVideoViewHolder.mPlayButton.setVisibility(View.VISIBLE);
            mTvSeriesVideoViewHolder.mPlayButtonCircle.setVisibility(View.VISIBLE);
            mTvSeriesVideoViewHolder.mPlayButton.setColorFilter(swatch.getTitleTextColor());
        } else {
            mTvSeriesVideoViewHolder.mPlayButton.setVisibility(View.GONE);
            mTvSeriesVideoViewHolder.mPlayButtonCircle.setVisibility(View.GONE);
        }
    }
}
