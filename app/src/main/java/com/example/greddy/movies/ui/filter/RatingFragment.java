package com.example.greddy.movies.ui.filter;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.greddy.movies.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by greddy on 7/12/2017.
 */

public class RatingFragment extends Fragment {

    private SharedPreferences.Editor mSharedPreferencesEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.rating_fragment_view, container, false);
        final TextView seekValue = view.findViewById(R.id.seek_bar_value);
        SeekBar ratingSeekBar = view.findViewById(R.id.seek_bar_rating);
        seekValue.setText(String.valueOf(ratingSeekBar.getProgress()));
        ratingSeekBar.incrementProgressBy((int)0.1);
        mSharedPreferencesEditor = container.getContext().getSharedPreferences(getString(R.string.movie_filter_shared_preferences), MODE_PRIVATE).edit();
        ratingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekValue.setText(String.valueOf(progress));
                mSharedPreferencesEditor.putInt(getString(R.string.movie_filter_rating),progress);
                mSharedPreferencesEditor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }
}
