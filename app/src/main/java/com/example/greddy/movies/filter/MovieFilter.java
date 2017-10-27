package com.example.greddy.movies.filter;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Genre;

import java.util.List;

import static com.example.greddy.movies.R.id.movie_filter_viewpager;

/**
 * Created by greddy on 7/11/2017.
 */

public class MovieFilter extends DialogFragment {

    private static final String TAG = MovieFilter.class.getSimpleName();
    private Dialog mDialog;
    private ImageView mCloseDialog;
    private ViewPager mViewPager;
    private Button mApplyFilters;
    private TabLayout mTabLayout;
    private RelativeLayout mParentLayout;
    private MovieFilterAdapter mMovieFilterAdapter;
    private List<Genre> mGenreList;

    public MovieFilter() {

    }

    @Override
    public void onStart() {
        super.onStart();
        mDialog = getDialog();
        if (mDialog != null) {
            WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
            params.gravity = Gravity.BOTTOM | Gravity.CENTER;
            mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            mDialog.getWindow().setAttributes(params);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.movies_filter_activity, container, false);
        mParentLayout = (RelativeLayout) view.findViewById(R.id.movies_filter_content);
        mParentLayout = (RelativeLayout) view.findViewById(R.id.movies_filter_content);
        mParentLayout.setMinimumHeight(getResources().getDisplayMetrics().heightPixels / 2);
        mViewPager = (ViewPager) view.findViewById(movie_filter_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.movies_filter_tab);
        mApplyFilters = (Button) view.findViewById(R.id.movie_apply_filter);
        setCancelable(false);
        mCloseDialog = (ImageView) view.findViewById(R.id.movies_filter_close);
        mCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mApplyFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mMovieFilterAdapter = new MovieFilterAdapter(getChildFragmentManager());
        mTabLayout.setupWithViewPager(mViewPager);
        mMovieFilterAdapter.addFragment(new YearFragment());
        mMovieFilterAdapter.addFragment(new RatingFragment());
        mMovieFilterAdapter.addFragment(new GenreFragment());
        mViewPager.setAdapter(mMovieFilterAdapter);
        return view;
    }
}
