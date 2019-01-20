package com.example.greddy.movies.tvseriesdetail.season;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Genre;
import com.example.greddy.movies.model.TvSeriesDetail;
import com.example.greddy.movies.tvseriesdetail.TvSeriesDetailsActivity;

import java.util.List;

/**
 * Created by greddy on 8/1/2017.
 */

public class TvSeriesInfoFragment extends Fragment {

    public static final String TAG = TvSeriesInfoFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_details_info_view, container, false);
        loadView(view);
        return view;
    }

    private void loadView(View view) {
        TextView mOverview = view.findViewById(R.id.tv_details_info_overview);
        TextView mReleaseDate = view.findViewById(R.id.tv_series_detail_release_date);
        RatingBar mRatingBar = view.findViewById(R.id.tv_series_detail_ratingBar);
        TextView mNoOfRatings = view.findViewById(R.id.tv_series_detail_no_of_ratings);
        TextView mTvSeriesGenre = view.findViewById(R.id.tv_series_detail_genre);
        TvSeriesDetailsActivity activity = (TvSeriesDetailsActivity) getActivity();
        TvSeriesDetail tvSeriesDetail = activity.getTvSeriesDetails();

        mOverview.setText(tvSeriesDetail.getOverview());
        mReleaseDate.setText(tvSeriesDetail.getFirstAirDate());
        float rating = (tvSeriesDetail.getVoteAverage() / 2);
        mRatingBar.setRating(rating);
        String voteCount = String.valueOf(tvSeriesDetail.getVoteCount()).concat(" ").concat(getResources().getString(R.string.ratings));
        mNoOfRatings.setText(voteCount);

        List<Genre> genreIds = tvSeriesDetail.getGenreIds();
        StringBuilder genre = new StringBuilder();
        for (int i = 0; i < genreIds.size(); i++) {
            genre.append(genreIds.get(i).getName());
            if (i == genreIds.size() - 1)
                genre.append("");
            else
                genre.append(" | ");
        }
        mTvSeriesGenre.setText(genre.toString());
    }
}
