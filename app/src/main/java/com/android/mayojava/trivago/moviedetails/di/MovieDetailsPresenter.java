package com.android.mayojava.trivago.moviedetails.di;

import android.support.annotation.NonNull;

import com.android.mayojava.trivago.moviedetails.MovieDetailsContract;
import com.android.mayojava.trivago.repository.MovieDataRepository;

import javax.inject.Inject;

/**
 * Movie details Presenter
 */
public class MovieDetailsPresenter implements MovieDetailsContract.Presenter {
    @NonNull
    private final MovieDetailsContract.View mView;

    @Inject
    public MovieDetailsPresenter(MovieDetailsContract.View view) {
        this.mView = view;

    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void hideProgressLoaders() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void loadPosterImage(String url) {
        if (url != null) {
            mView.loadMoviePosterImage(url);
        }
    }

    @Override
    public void setTitle(String title) {
        if (title != null) {
            mView.setMovieTitle(title);
        }
    }

    @Override
    public void setOverview(String overview) {
        if (overview != null) {
            mView.setMovieOverview(overview);
        }
    }

    @Override
    public void setTagline(String tagline) {
        if (tagline != null) {
            mView.setMovieTagline(tagline);
        }
    }

    @Override
    public void setRatings(Double ratings) {
        if (ratings != null) {
            String res = String.format("%.1f", (ratings/10.0) * 5.0);
            mView.showRatings(Float.parseFloat(res));
        }
    }

    @Override
    public void setTrailer(String trailerUrl) {
        if (trailerUrl != null) {
            mView.setMovieTrailerUrl(trailerUrl);
        }
    }

    @Override
    public void setGenre(String genre) {
        if (genre != null) {
            mView.setGenre(genre);
        }
    }

    @Override
    public void setYear(String year) {
        if (year != null) {
            mView.setYear(year);
        }
    }
}
