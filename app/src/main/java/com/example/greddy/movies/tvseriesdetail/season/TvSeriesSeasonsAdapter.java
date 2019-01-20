package com.example.greddy.movies.tvseriesdetail.season;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Season;
import com.example.greddy.movies.model.TvSeriesDetail;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.tvseriesdetail.episode.TvSeriesEpisodeDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.greddy.movies.tvseriesdetail.episode.TvSeriesEpisodeFragment.SEASON_NAME;
import static com.example.greddy.movies.tvseriesdetail.episode.TvSeriesEpisodeFragment.SEASON_NUMBER;
import static com.example.greddy.movies.tvseriesdetail.episode.TvSeriesEpisodeFragment.SERIES_NUMBER;

/**
 * Created by greddy on 8/3/2017.
 */

class TvSeriesSeasonsAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private TvSeriesDetail mTvSeriesDetail;
    private ArrayList<Season> mSeasons;

    public TvSeriesSeasonsAdapter(Context context) {
        mContext = context;
    }

    public void setSeasonsData(TvSeriesDetail tvSeriesDetail) {
        mTvSeriesDetail = tvSeriesDetail;
        mSeasons = (ArrayList<Season>) tvSeriesDetail.getSeasons();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TvSeriesSeasonsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.tv_series_seasons_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TvSeriesSeasonsViewHolder viewHolder = (TvSeriesSeasonsViewHolder) holder;
        String posterPath = ApiClient.IMAGE_W500 + mSeasons.get(position).getPosterPath();
        Picasso.with(mContext).load(posterPath).into(viewHolder.mPoster);
        int seasonNo = mSeasons.get(position).getSeasonNumber();
        viewHolder.mSeason.setText(mContext.getString(R.string.season_lbl).concat(" ").concat(String.valueOf(seasonNo)));
    }

    @Override
    public int getItemCount() {
        return mSeasons != null ? mSeasons.size() : 0;
    }

    class TvSeriesSeasonsViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mPoster;
        private final TextView mSeason;

        TvSeriesSeasonsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, TvSeriesEpisodeDetailsActivity.class);
                intent.putExtra(SERIES_NUMBER, mTvSeriesDetail.getId());
                intent.putExtra(SEASON_NUMBER, mSeasons.get(getAdapterPosition()).getSeasonNumber());
                intent.putExtra(SEASON_NAME, mTvSeriesDetail.getName());
                mContext.startActivity(intent);
            });
            mPoster = itemView.findViewById(R.id.season_poster);
            mSeason = itemView.findViewById(R.id.season_no);
        }
    }
}
