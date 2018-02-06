package com.anims;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.materialdesign.R;

public class WidgetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    TextView tvBtnStartAnim;
    Spinner spinner;
    ImageView imageView, imgBack;
    int animId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getSharedPreferences("prefs", MODE_PRIVATE).getInt("theme", R.style.ThemeBlue));
        setContentView(R.layout.activity_widget_anims);
        setTitle("View Animation");
        init();
        setSpinner();
    }

    private void init() {
        tvBtnStartAnim = findViewById(R.id.tv_btn_widget);
        spinner = findViewById(R.id.spinner_widget);
        imageView = findViewById(R.id.img_widget);
        Glide.with(this).asGif().load(R.drawable.orio2).into(imageView);
        tvBtnStartAnim.setOnClickListener(this);
        tvBtnStartAnim.setVisibility(View.INVISIBLE);
        imgBack = findViewById(R.id.item_image_back);
        imgBack.setOnClickListener(this);
    }

    private void setSpinner() {
        String[] spinArray = new String[]{"Select an animation", "Fade", "Scale",
                "Translate", "Push left in",
                "Push left out", "Slide right", "Down from up", "Up from bottom",
                "Rotate Clockwise", "Rotate anti clockwise"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinArray);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position <= 0) {
            tvBtnStartAnim.setVisibility(View.INVISIBLE);
        } else if (position > 0) {
            tvBtnStartAnim.setVisibility(View.VISIBLE);
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
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_btn_widget && animId != 0) {
            imageView.clearAnimation();
            imageView.startAnimation(AnimationUtils.loadAnimation(this, animId));
        } else if (v.getId() == R.id.item_image_back) {
            onBackPressed();
        }
    }
}
