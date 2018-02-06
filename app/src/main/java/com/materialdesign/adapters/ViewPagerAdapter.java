package com.materialdesign.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by compindia-fujitsu on 09-11-2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;
    ArrayList<String> fragments_titles;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments_titles = new ArrayList<>();
        fragments = new ArrayList<>();

    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments_titles.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        fragments.add(fragment);
        fragments_titles.add(title);
    }
}
