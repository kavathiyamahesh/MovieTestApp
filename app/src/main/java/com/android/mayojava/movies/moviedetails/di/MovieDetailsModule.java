package com.android.mayojava.movies.moviedetails.di;

import com.android.mayojava.movies.moviedetails.MovieDetailsContract;
import com.android.mayojava.movies.popularmovies.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Module that provides the MovieDetails view
 */
@Module
public class MovieDetailsModule {
    private final MovieDetailsContract.View mView;

    public MovieDetailsModule(MovieDetailsContract.View view) {
        this.mView = view;
    }

    @Provides
    @FragmentScoped
    MovieDetailsContract.View providesMovieDetailsContractView() {
        return this.mView;
    }
}
