package com.anims;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.anims.adapters.ListViewAdapter;
import com.materialdesign.R;

public class ListViewActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    ListViewAdapter listViewAdapter;
    TextView tvBtn_ListView;
    Spinner spinnerListView;
    ListView listView;
    int animId;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getSharedPreferences("prefs", MODE_PRIVATE).getInt("theme", R.style.ThemeBlue));
        setContentView(R.layout.activity_list_view_anims);
        setTitle("ListView Animations");
        init();
        addData();
    }

    private void init() {
        tvBtn_ListView = findViewById(R.id.tv_btn_listView);
        spinnerListView = findViewById(R.id.spinner_list_view);
        listView = findViewById(R.id.listView);
        tvBtn_ListView.setOnClickListener(this);
        tvBtn_ListView.setVisibility(View.INVISIBLE);
        imgBack = findViewById(R.id.item_image_back);
        imgBack.setOnClickListener(this);
    }

    private void addData() {
        String[] spinArray = new String[]{"Select an animation", "Fade", "Scale",
                "Translate", "Push left in",
                "Push left out", "Slide right", "Down from up", "Up from bottom",
                "Rotate Clockwise", "Rotate anti clockwise"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinArray);
        spinnerListView.setAdapter(arrayAdapter);
        spinnerListView.setOnItemSelectedListener(this);
        listViewAdapter = new ListViewAdapter(this);
        listView.setAdapter(listViewAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_btn_listView && animId != 0) {
            if (listView.getAdapter() == null) {
                listViewAdapter = new ListViewAdapter(this);
                listView.setAdapter(listViewAdapter);
            } else {
                listViewAdapter.notifyDataSetChanged();
            }
            LayoutAnimationController animationController = new LayoutAnimationController(AnimationUtils.loadAnimation(this, animId), 0.7f);
            listView.setLayoutAnimation(animationController);
        } else if (v.getId() == R.id.item_image_back) {
            onBackPressed();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position <= 0) {
            tvBtn_ListView.setVisibility(View.INVISIBLE);
        } else if (position > 0) {
            tvBtn_ListView.setVisibility(View.VISIBLE);
            switch (position) {
                case 1:
                    animId = R.anim.fade_in_out;
                    break;
                case 2:
                    animId = R.anim.scale_translate;
                    break;
                case 3:
                    animId = R.anim.translate;
                    break;
                case 4:
                    animId = R.anim.push_left_in;
                    break;
                case 5:
                    animId = R.anim.push_left_out;
                    break;
                case 6:
                    animId = R.anim.slide_right;
                    break;
                case 7:
                    animId = R.anim.down_from_up;
                    break;
                case 8:
                    animId = R.anim.up_from_bottom;
                    break;
                case 9:
                    animId = R.anim.rotate_clockwise;
                    break;
                case 10:
                    animId = R.anim.rotate_anti_clockwise;
                    break;
                default:
                    animId = 0;
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
