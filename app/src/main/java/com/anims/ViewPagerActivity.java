package com.anims;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.anims.adapters.ViewPagerAdapter;
import com.eftimoff.viewpagertransformers.AccordionTransformer;
import com.eftimoff.viewpagertransformers.BackgroundToForegroundTransformer;
import com.eftimoff.viewpagertransformers.CubeInTransformer;
import com.eftimoff.viewpagertransformers.CubeOutTransformer;
import com.eftimoff.viewpagertransformers.DepthPageTransformer;
import com.eftimoff.viewpagertransformers.DrawFromBackTransformer;
import com.eftimoff.viewpagertransformers.FlipHorizontalTransformer;
import com.eftimoff.viewpagertransformers.FlipVerticalTransformer;
import com.eftimoff.viewpagertransformers.ForegroundToBackgroundTransformer;
import com.eftimoff.viewpagertransformers.RotateDownTransformer;
import com.eftimoff.viewpagertransformers.RotateUpTransformer;
import com.eftimoff.viewpagertransformers.StackTransformer;
import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.eftimoff.viewpagertransformers.ZoomInTransformer;
import com.eftimoff.viewpagertransformers.ZoomOutSlideTransformer;
import com.eftimoff.viewpagertransformers.ZoomOutTranformer;
import com.materialdesign.R;

public class ViewPagerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    ViewPager viewPager;
    Spinner spinner;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getSharedPreferences("prefs", MODE_PRIVATE).getInt("theme", R.style.ThemeBlue));
        setContentView(R.layout.activity_view_pager_anims);
        setTitle("ViewPager Transformations");
        init();
        setSpinner();
    }

    private void init() {
        viewPager = findViewById(R.id.view_pager);
        spinner = findViewById(R.id.spinner_viewPager);
        imgBack = findViewById(R.id.item_image_back);
        imgBack.setOnClickListener(this);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    private void setSpinner() {
        String[] spinArray = new String[]{"Default Transformer",
                "AccordionTransformer",
                "BackgroundToForegroundTransformer",
                "CubeInTransformer",
                "CubeOutTransformer",
                "DepthPageTransformer",
                "DrawFromBackTransformer",
                "FlipHorizontalTransformer",
                "FlipVerticalTransformer",
                "ForegroundToBackgroundTransformer",
                "RotateDownTransformer",
                "RotateUpTransformer",
                "StackTransformer",
                "TabletTransformer",
                "ZoomInTransformer",
                "ZoomOutSlideTransformer",
                "ZoomOutTranformer"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinArray);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
            viewPager.clearAnimation();
            switch (position) {
                case 1:
                    viewPager.setPageTransformer(true, new AccordionTransformer());
                    break;
                case 2:
                    viewPager.setPageTransformer(true, new BackgroundToForegroundTransformer());
                    break;
                case 3:
                    viewPager.setPageTransformer(true, new CubeInTransformer());
                    break;
                case 4:
                    viewPager.setPageTransformer(true, new CubeOutTransformer());
                    break;
                case 5:
                    viewPager.setPageTransformer(true, new DepthPageTransformer());
                    break;
                case 6:
                    viewPager.setPageTransformer(true, new DrawFromBackTransformer());
                    break;
                case 7:
                    viewPager.setPageTransformer(true, new FlipHorizontalTransformer());
                    break;
                case 8:
                    viewPager.setPageTransformer(true, new FlipVerticalTransformer());
                    break;
                case 9:
                    viewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
                    break;
                case 10:
                    viewPager.setPageTransformer(true, new RotateDownTransformer());
                    break;
                case 11:
                    viewPager.setPageTransformer(true, new RotateUpTransformer());
                    break;
                case 12:
                    viewPager.setPageTransformer(true, new StackTransformer());
                    break;
                case 13:
                    viewPager.setPageTransformer(true, new TabletTransformer());
                    break;
                case 14:
                    viewPager.setPageTransformer(true, new ZoomInTransformer());
                    break;
                case 15:
                    viewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
                    break;
                case 16:
                    viewPager.setPageTransformer(true, new ZoomOutTranformer());
                    break;
                default:
                    viewPager.clearAnimation();
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
        overridePendingTransition(0,0);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.item_image_back) {
            onBackPressed();
        }
    }
}
