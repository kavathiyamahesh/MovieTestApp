package com.android.mayojava.movies.moviedetails.di;

import com.android.mayojava.movies.dagger.components.ApiComponent;
import com.android.mayojava.movies.moviedetails.MovieDetailsActivity;
import com.android.mayojava.movies.popularmovies.di.FragmentScoped;

import dagger.Component;

/**
 * Component to inject the Movie  details activity
 */
@FragmentScoped
@Component(modules = {MovieDetailsModule.class}, dependencies = {ApiComponent.class})
public interface MovieDetailsComponent {
    void injectMovieDetailsActivity(MovieDetailsActivity movieDetailsActivity);
}
