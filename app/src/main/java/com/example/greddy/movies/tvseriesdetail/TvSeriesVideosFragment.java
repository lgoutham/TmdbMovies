package com.example.greddy.movies.tvseriesdetail;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.TvSeriesDetail;
import com.example.greddy.movies.model.VideoResponse;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by greddy on 8/7/2017.
 */

public class TvSeriesVideosFragment extends Fragment {

    public static final String TAG = TvSeriesVideosFragment.class.getSimpleName();

    private ApiInterface mApiInterface;
    private Context mContext;
    private TvSeriesVideosAdapter mTvSeriesVideosAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View view = LayoutInflater.from(mContext).inflate(R.layout.tv_series_images_view, container, false);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        loadView(view);
        return view;
    }

    private void loadView(View view) {
        RecyclerView mVideos = view.findViewById(R.id.tv_series_images);
        mVideos.setLayoutManager(new LinearLayoutManager(mContext));
        mTvSeriesVideosAdapter = new TvSeriesVideosAdapter(mContext);
        mVideos.setAdapter(mTvSeriesVideosAdapter);
        TvSeriesDetailsActivity activity = (TvSeriesDetailsActivity) getActivity();
        TvSeriesDetail tvSeriesDetail = activity.getTvSeriesDetails();
        Call<VideoResponse> videosCall = mApiInterface.getTvSeriesVideos(tvSeriesDetail.getId(), ApiClient.API_KEY);
        videosCall.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                mTvSeriesVideosAdapter.updateData(response.body().getVideoList());
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

            }
        });
    }

}
