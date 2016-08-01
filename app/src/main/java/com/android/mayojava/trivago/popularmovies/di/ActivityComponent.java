package com.android.mayojava.trivago.popularmovies.di;

import com.android.mayojava.trivago.dagger.components.ApiComponent;
import com.android.mayojava.trivago.popularmovies.PopularMoviesActivity;

import dagger.Component;

/**
 *
 */
@Component(modules = ActivityPresenterModule.class, dependencies = {ApiComponent.class})
@FragmentScoped
public interface ActivityComponent {
    void injectPopularMoviesActivity(PopularMoviesActivity popularMoviesActivity);
}
