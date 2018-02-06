package com.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.materialdesign.R;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DetailedActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView, imgBackArrow;
    TextView title, year, director, music, starcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getSharedPreferences("prefs", MODE_PRIVATE).getInt("theme", R.style.ThemeBlue));
        setContentView(R.layout.activity_main2_recycler);
        setTitle("Movie Details");
        imgBackArrow = findViewById(R.id.item_image_back);
        imgBackArrow.setOnClickListener(this);
        imageView = findViewById(R.id.item_image);
        title = findViewById(R.id.item_title);
        year = findViewById(R.id.item_year);
        director = findViewById(R.id.item_director);
        music = findViewById(R.id.item__music_director);
        starcast = findViewById(R.id.item_starcast);
        Intent intent = getIntent();
        if (intent != null) {
            MoviesData moviesData = (MoviesData) intent.getSerializableExtra("movieData");
//            imageView.setImageResource(moviesData.getMovieImage());
            Glide.with(this).load(moviesData.getMovieImage())
                    .apply(bitmapTransform(new CircleCrop()))
                    .into(imageView);
            title.setText(moviesData.getMovieName());
            year.setText(moviesData.getMovieYear());
            director.setText(moviesData.getMovieDirector());
            music.setText(moviesData.getMovieMusic());
            starcast.setText(moviesData.getMovieStarcast());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.item_image_back) {
            onBackPressed();
        }
    }
}
