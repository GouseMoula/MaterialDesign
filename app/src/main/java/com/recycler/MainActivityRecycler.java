package com.recycler;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.materialdesign.R;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivityRecycler extends AppCompatActivity implements RecyclerAdapter.ItemClickListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    ArrayList<MoviesData> moviesData;
    ImageView imgToolbar, imgBackArrow;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    boolean switchToGrid = true;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getSharedPreferences("prefs", MODE_PRIVATE).getInt("theme", R.style.ThemeBlue));
        setContentView(R.layout.activity_main_recycler);
        init();
        addMovieData();
        setRecyclerView(false);
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        imgToolbar = findViewById(R.id.img_toolbar);
        imgToolbar.setOnClickListener(this);
        refreshLayout = findViewById(R.id.swipeDown);
        refreshLayout.setOnRefreshListener(this);
        imgBackArrow = findViewById(R.id.item_image_back);
        imgBackArrow.setOnClickListener(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(this, 2);
    }

    private void addMovieData() {
        moviesData = new ArrayList<>();
        moviesData.add(new MoviesData(R.drawable.movie1_image, R.string.movie_1_title, R.string.year_2018, R.string.movie_1_director, R.string.movie_1_starcast, R.string.movie_1_music_director));
        moviesData.add(new MoviesData(R.drawable.movie2_image, R.string.movie_2_title, R.string.year_2017, R.string.movie_2_director, R.string.movie_2_starcast, R.string.movie_2_music_director));
        moviesData.add(new MoviesData(R.drawable.movie3_image, R.string.movie_3_title, R.string.year_2017, R.string.movie_3_director, R.string.movie_3_starcast, R.string.movie_3_music_director));
        moviesData.add(new MoviesData(R.drawable.movie4_image, R.string.movie_4_title, R.string.year_2018, R.string.movie_4_director, R.string.movie_4_starcast, R.string.movie_4_music_director));
        moviesData.add(new MoviesData(R.drawable.movie5_image, R.string.movie_5_title, R.string.year_2018, R.string.movie_5_director, R.string.movie_5_starcast, R.string.movie_5_music_director));
        moviesData.add(new MoviesData(R.drawable.movie6_image, R.string.movie_6_title, R.string.year_2018, R.string.movie_6_director, R.string.movie_6_starcast, R.string.movie_6_music_director));
        moviesData.add(new MoviesData(R.drawable.movie7_image, R.string.movie_7_title, R.string.year_2018, R.string.movie_7_director, R.string.movie_7_starcast, R.string.movie_7_music_director));
        moviesData.add(new MoviesData(R.drawable.aven, R.string.movie_8_title, R.string.year_2018, R.string.movie_8_director, R.string.movie_8_starcast, R.string.movie_8_music_director));
        moviesData.add(new MoviesData(R.drawable.movie9_image, R.string.movie_9_title, R.string.year_2018, R.string.movie_9_director, R.string.movie_9_starcast, R.string.movie_9_music_director));
        moviesData.add(new MoviesData(R.drawable.movie10_image, R.string.movie_10_title, R.string.year_2018, R.string.movie_10_director, R.string.movie_10_starcast, R.string.movie_10_music_director));
        recyclerAdapter = new RecyclerAdapter(this, moviesData);
        recyclerAdapter.setClickListener(this);
        recyclerAdapter.setSwitchListGrid(false);
    }

    private void setRecyclerView(boolean isGridView) {
        recyclerAdapter.setSwitchListGrid(isGridView);
        recyclerAdapter.lastPosition = -1;
        recyclerView.setLayoutManager(isGridView ? gridLayoutManager : linearLayoutManager);
        if (recyclerView.getAdapter() == null)
            recyclerView.setAdapter(recyclerAdapter);
        else
            recyclerAdapter.notifyDataSetChanged();
    }

    private void changeToolBarImage(int image) {
        final Drawable drawable = getResources().getDrawable(image);
        final ImageView iv = ((ImageView) findViewById(R.id.img_toolbar));
        iv.setRotationX(0f);
        iv.animate().rotationX(90f).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                iv.setImageDrawable(drawable);
                iv.setRotationX(270f);
                iv.animate().rotationX(360f).setListener(null).setDuration(100);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }
        }).setDuration(100);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_toolbar:
                if (switchToGrid) {
                    setRecyclerView(true);
                    changeToolBarImage(R.drawable.ic_view_list);
                } else {
                    setRecyclerView(false);
                    changeToolBarImage(R.drawable.ic_apps);
                }
                switchToGrid = !switchToGrid;
                break;

            case R.id.item_image_back:
                onBackPressed();
                break;
        }

    }

    @Override
    public void onRefresh() {

        Collections.shuffle(moviesData);
        recyclerAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

//    @Override
//    public void onBackPressed() {
//        if (recyclerAdapter.closePopUp()) {
//            super.onBackPressed();
//        }
//    }
}
