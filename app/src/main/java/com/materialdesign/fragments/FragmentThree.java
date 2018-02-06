package com.materialdesign.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.materialdesign.R;


public class FragmentThree extends Fragment {
    public FragmentThree() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        Spinner spinner = view.findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, new String[]{"Spinner Item 1", "Spinner Item 2", "Spinner Item 3", "Spinner Item 4"}));
        return view;
    }

}
