package com.example.greddy.movies.tvseriesdetail.episode;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greddy on 8/8/2017.
 */

public class TvSeriesEpisodeAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();

    public TvSeriesEpisodeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Info";
                break;
            case 1:
                title = "Episodes";
                break;
            case 2:
                title = "Image";
                break;
            case 3:
                title = "Videos";
                break;
        }
        return title;
    }
}
