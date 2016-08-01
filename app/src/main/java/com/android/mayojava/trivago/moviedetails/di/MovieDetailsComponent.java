package com.android.mayojava.trivago.moviedetails.di;

import com.android.mayojava.trivago.dagger.components.ApiComponent;
import com.android.mayojava.trivago.moviedetails.MovieDetailsActivity;
import com.android.mayojava.trivago.popularmovies.di.FragmentScoped;

import dagger.Component;

/**
 * Component to inject the Movie  details activity
 */
@FragmentScoped
@Component(modules = {MovieDetailsModule.class}, dependencies = {ApiComponent.class})
public interface MovieDetailsComponent {
    void injectMovieDetailsActivity(MovieDetailsActivity movieDetailsActivity);
}
