package com.example.greddy.movies.tvseriesdetail.episode;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.TvSeriesEpisodeImageResponse;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by greddy on 8/8/2017.
 */

public class TvSeriesEpisodeImageFragment extends Fragment {

    public static final String TAG = TvSeriesEpisodeImageFragment.class.getSimpleName();

    private ApiInterface mApiInterface;
    private Context mContext;
    private TvSeriesEpisodeImageAdpater mTvSeriesEpisodeImageAdpater;
    private static int mSeriesNo;
    private static int mSeasonNo;

    public static TvSeriesEpisodeImageFragment newInstance(int seriesNo, int seasonNo) {
        mSeriesNo = seriesNo;
        mSeasonNo = seasonNo;
        return new TvSeriesEpisodeImageFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_series_episode_images_view, container, false);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        loadView(view);
        return view;
    }

    private void loadView(View view) {
        ViewPager mImages = view.findViewById(R.id.tv_series_episode_images);
        int pageMargin = (int) (getResources().getDimension(R.dimen.global_default_dimen_10px));
        mImages.setPageMargin(pageMargin);

        mTvSeriesEpisodeImageAdpater = new TvSeriesEpisodeImageAdpater(mContext);
        mImages.setAdapter(mTvSeriesEpisodeImageAdpater);

        if (mSeasonNo > -1 && mSeasonNo > -1) {
            Call<TvSeriesEpisodeImageResponse> episodeImageResponseCall = mApiInterface.getTvSeriesEpisodeImages(mSeriesNo, mSeasonNo, ApiClient.API_KEY);
            episodeImageResponseCall.enqueue(new Callback<TvSeriesEpisodeImageResponse>() {
                @Override
                public void onResponse(Call<TvSeriesEpisodeImageResponse> call, Response<TvSeriesEpisodeImageResponse> response) {
                    mTvSeriesEpisodeImageAdpater.updateData(response.body().getPosters());
                }

                @Override
                public void onFailure(Call<TvSeriesEpisodeImageResponse> call, Throwable t) {

                }
            });
        }
    }
}
