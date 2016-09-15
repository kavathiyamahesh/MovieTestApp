package com.android.mayojava.movies.popularmovies.di;

import com.android.mayojava.movies.dagger.components.ApiComponent;
import com.android.mayojava.movies.popularmovies.PopularMoviesActivity;

import dagger.Component;

/**
 *
 */
@Component(modules = ActivityPresenterModule.class, dependencies = {ApiComponent.class})
@FragmentScoped
public interface ActivityComponent {
    void injectPopularMoviesActivity(PopularMoviesActivity popularMoviesActivity);
}
