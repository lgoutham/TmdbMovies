package com.example.greddy.movies.home;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greddy on 7/18/2017.
 */

public class TmdbAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();

    public TmdbAdapter(FragmentManager fm) {
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
        if (position == 0)
            return "Movies";
        else
            return "Tv Series";
    }
}
