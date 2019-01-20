package com.example.greddy.movies.tvseriesdetail.season;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.TvSeriesDetail;
import com.example.greddy.movies.tvseriesdetail.TvSeriesDetailsActivity;

/**
 * Created by greddy on 8/3/2017.
 */

public class TvSeriesSeasonsFragment extends Fragment {

    public static final String TAG = TvSeriesSeasonsFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_series_seasons_view, container, false);
        loadView(view);
        return view;
    }

    private void loadView(View view) {
        RecyclerView tvSeriesSeasons = view.findViewById(R.id.tv_series_seasons_list);
        tvSeriesSeasons.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        TvSeriesSeasonsAdapter seriesSeasonsAdapter = new TvSeriesSeasonsAdapter(getActivity());
        TvSeriesDetailsActivity activity = (TvSeriesDetailsActivity) getActivity();
        TvSeriesDetail tvSeriesDetail = activity.getTvSeriesDetails();
        seriesSeasonsAdapter.setSeasonsData(tvSeriesDetail);
        tvSeriesSeasons.setAdapter(seriesSeasonsAdapter);
    }
}
