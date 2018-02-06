package com.anims.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.anims.BlankFragment;
import com.materialdesign.R;

import java.util.ArrayList;

/**
 * Created by compindia-fujitsu on 14-11-2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragmentsList;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentsList = new ArrayList<>();

        BlankFragment blankFragment1 = new BlankFragment();
        BlankFragment blankFragment2 = new BlankFragment();
        BlankFragment blankFragment3 = new BlankFragment();
        BlankFragment blankFragment4 = new BlankFragment();

        fragmentsList.add(blankFragment1);
        fragmentsList.add(blankFragment2);
        fragmentsList.add(blankFragment3);
        fragmentsList.add(blankFragment4);

        blankFragment1.changeBackground(R.color.color1);
        blankFragment2.changeBackground(R.color.color2);
        blankFragment3.changeBackground(R.color.color3);
        blankFragment4.changeBackground(R.color.color4);
        blankFragment1.changeText("1");
        blankFragment2.changeText("2");
        blankFragment3.changeText("3");
        blankFragment4.changeText("4");

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }
}
