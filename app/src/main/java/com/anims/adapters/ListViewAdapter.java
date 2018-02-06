package com.anims.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.materialdesign.R;

import java.util.ArrayList;

/**
 * Created by compindia-fujitsu on 14-11-2017.
 */

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListData> list;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list = new ArrayList<ListData>();
        list.add(new ListData());
        list.add(new ListData());
        list.add(new ListData());
        list.add(new ListData());
        list.add(new ListData());
        list.add(new ListData());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_item_anims, parent, false);
        return convertView;
    }

    public class ListData {
    }
}
