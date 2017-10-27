package com.example.greddy.movies.tvseriesdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.ImageResponse;
import com.example.greddy.movies.model.TvSeriesDetail;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by greddy on 8/7/2017.
 */

public class TvSeriesImagesFragment extends Fragment {

    public static final String TAG = TvSeriesImagesFragment.class.getSimpleName();

    private ApiInterface mApiInterface;
    private Context mContext;
    private TvSeriesImagesAdapter mTvSeriesImagesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_series_images_view, container, false);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        loadView(view);
        return view;
    }

    private void loadView(View view) {
        RecyclerView mImages = view.findViewById(R.id.tv_series_images);
        mImages.setLayoutManager(new LinearLayoutManager(mContext));
        mTvSeriesImagesAdapter = new TvSeriesImagesAdapter(mContext);
        mImages.setAdapter(mTvSeriesImagesAdapter);
        TvSeriesDetailsActivity activity = (TvSeriesDetailsActivity) getActivity();
        TvSeriesDetail tvSeriesDetail = activity.getTvSeriesDetails();
        Call<ImageResponse> imagesCall = mApiInterface.getTvSeriesImages(tvSeriesDetail.getId(), ApiClient.API_KEY);
        imagesCall.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                mTvSeriesImagesAdapter.updateData(response.body().getBackdrops());
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }
}
