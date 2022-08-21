package com.example.greddy.movies.ui.filter;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greddy.movies.R;

/**
 * Created by greddy on 7/12/2017.
 */

public class YearFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.year_fragment_view, container, false);
    }
}
