package com.example.greddy.movies.filter;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.GenreResponse;
import com.example.greddy.movies.rest.ApiClient;
import com.example.greddy.movies.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by greddy on 7/12/2017.
 */

public class GenreFragment extends Fragment {

    public static final String TAG = GenreFragment.class.getSimpleName();
    private GenreAdapter mGenreAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.genre_fragment_view, container, false);
        /*int[] mGenreImages = new int[]{R.drawable.video_player, R.drawable.gun, R.drawable.map, R.drawable.bear_face, R.drawable.theater_masks, R.drawable.knife, R.drawable.video_camera, R.drawable.pierrot, R.drawable.family};*/
        GridView genreGrid = (GridView) view.findViewById(R.id.genre_view);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GenreResponse> genreListCall = apiInterface.getGenreList(ApiClient.API_KEY);
        genreListCall.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getGenreList().size());
                mGenreAdapter.UpdateGenreList(response.body().getGenreList());
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        mGenreAdapter = new GenreAdapter(this.getActivity());
        genreGrid.setAdapter(mGenreAdapter);
        return view;
    }
}
