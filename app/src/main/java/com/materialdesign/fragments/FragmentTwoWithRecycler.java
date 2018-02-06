package com.materialdesign.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.materialdesign.R;
import com.materialdesign.ThemeInterface;
import com.materialdesign.ThemesData;
import com.materialdesign.adapters.RecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by compindia-fujitsu on 12-11-2017.
 */

public class FragmentTwoWithRecycler extends Fragment implements RecyclerAdapter.ItemClickListener {
    ArrayList<ThemesData> themesData;

    ThemeInterface themeInterface;

    public FragmentTwoWithRecycler() {
        themesData = new ArrayList<>();
        themesData.add(new ThemesData(R.string.list_item_text_1, R.color.colorPrimaryRed, R.style.ThemeRed));
        themesData.add(new ThemesData(R.string.list_item_text_2, R.color.colorPrimaryPink, R.style.ThemePink));
        themesData.add(new ThemesData(R.string.list_item_text_3, R.color.colorPrimaryPurple, R.style.ThemePurple));
        themesData.add(new ThemesData(R.string.list_item_text_4, R.color.colorPrimaryIndigo, R.style.ThemeIndigo));
        themesData.add(new ThemesData(R.string.list_item_text_5, R.color.colorPrimaryBlue, R.style.ThemeBlue));
        themesData.add(new ThemesData(R.string.list_item_text_6, R.color.colorPrimaryTeal, R.style.ThemeTeal));
        themesData.add(new ThemesData(R.string.list_item_text_7, R.color.colorPrimaryGreen, R.style.ThemeGreen));
        themesData.add(new ThemesData(R.string.list_item_text_8, R.color.colorPrimaryOrange, R.style.ThemeOrange));
        themesData.add(new ThemesData(R.string.list_item_text_9, R.color.colorPrimaryDeepOrange, R.style.ThemeDeepOrange));
        themesData.add(new ThemesData(R.string.list_item_text_10, R.color.colorPrimaryBrown, R.style.ThemeBrown));
        themesData.add(new ThemesData(R.string.list_item_text_11, R.color.colorPrimaryBlueGray, R.style.ThemeBlueGray));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two_with_recycler, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), themesData);
        recyclerAdapter.setClickListener(this);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        themeInterface = (ThemeInterface) getActivity();
        if (themeInterface != null) {
            themeInterface.changeTheme(themesData.get(position).getTheme());
        }
    }
}
