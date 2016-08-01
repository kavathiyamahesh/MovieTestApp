package com.android.mayojava.trivago.moviedetails.di;

import com.android.mayojava.trivago.moviedetails.MovieDetailsContract;
import com.android.mayojava.trivago.popularmovies.di.FragmentScoped;

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
