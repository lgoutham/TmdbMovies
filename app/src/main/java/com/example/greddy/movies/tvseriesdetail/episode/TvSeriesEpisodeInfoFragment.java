package com.example.greddy.movies.tvseriesdetail.episode;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.SeasonResponse;

/**
 * Created by greddy on 8/8/2017.
 */

public class TvSeriesEpisodeInfoFragment extends Fragment {

    public static final String TAG = TvSeriesEpisodeInfoFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_series_season_info_view, container, false);
        loadView(view);
        return view;
    }

    private void loadView(View view) {
        TextView mSeasonName = view.findViewById(R.id.tv_series_season_name);
        TextView mOverview = view.findViewById(R.id.tv_series_season_overview);
        TextView mReleaseDate = view.findViewById(R.id.tv_series_season_release_date);
        TvSeriesEpisodeDetailsActivity activity = (TvSeriesEpisodeDetailsActivity) getActivity();
        SeasonResponse seasonResponse = activity.getTvSeriesSeasonResponse();

        mSeasonName.setText(seasonResponse.getName());
        mOverview.setText(seasonResponse.getOverview());
        mReleaseDate.setText(seasonResponse.getAirDate());
    }
}
