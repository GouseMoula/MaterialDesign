package com.materialdesign.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.materialdesign.R;
import com.materialdesign.ThemeInterface;
import com.materialdesign.ThemesData;

import java.util.ArrayList;


public class FragmentTwo extends Fragment implements AdapterView.OnItemClickListener {
    ArrayList<ThemesData> themesData;
    ListView listView;
    Context context;
    LayoutInflater inflater;
    ThemeInterface themeInterface;

    public FragmentTwo() {
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
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        listView = view.findViewById(R.id.list_view);
        listView.setAdapter(new ListAdapter());
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        themeInterface = (ThemeInterface) getActivity();
        if (themeInterface != null) {
            themeInterface.changeTheme(themesData.get(position).getTheme());
        }
    }

    public class ListAdapter extends BaseAdapter {
        ListAdapter() {
            context = getContext();
            inflater = (LayoutInflater) (context != null ? context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) : null);
        }

        @Override
        public int getCount() {
            return themesData.size();
        }

        @Override
        public Object getItem(int position) {
            return themesData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            TextView textView = convertView.findViewById(R.id.tv_list_item);
            textView.setText(themesData.get(position).getItemText());
            textView.setBackgroundColor(getResources().getColor(themesData.get(position).getItemBackgroundColor()));
            return convertView;
        }
    }

}
