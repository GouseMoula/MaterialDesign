package com.recycler;

import java.io.Serializable;

/**
 * Created by compindia-fujitsu on 16-11-2017.
 */

public class MoviesData implements Serializable {
    private int movieImage, movieName, movieYear, movieDirector, movieStarcast, movieMusic;

    public MoviesData(int movieImage, int movieName, int movieYear, int movieDirector, int movieStarcast, int movieMusic) {
        this.movieImage = movieImage;
        this.movieName = movieName;
        this.movieYear = movieYear;
        this.movieStarcast = movieStarcast;
        this.movieDirector = movieDirector;
        this.movieMusic = movieMusic;
    }

    public int getMovieImage() {
        return movieImage;
    }

    public int getMovieName() {
        return movieName;
    }

    public int getMovieDirector() {
        return movieDirector;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public int getMovieStarcast() {
        return movieStarcast;
    }

    public int getMovieMusic() {
        return movieMusic;
    }
}
