package com.anims;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.materialdesign.R;


public class BlankFragment extends Fragment {
    TextView textView;
    String text;
    int color = 0;

    public BlankFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (color != 0) {
            textView.setBackgroundColor(getContext().getResources().getColor(color));
        }
        if (text != null) {
            textView.setText(textView.getText().toString() + " " + text);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_anims, container, false);
        textView = view.findViewById(R.id.tv_blank_fragment);
        return view;
    }

    public void changeBackground(int color) {
        this.color = color;
    }

    public void changeText(String text) {
        this.text = text;
    }
}
