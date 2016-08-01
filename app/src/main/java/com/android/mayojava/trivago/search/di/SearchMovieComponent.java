package com.android.mayojava.trivago.search.di;

import com.android.mayojava.trivago.dagger.components.ApiComponent;
import com.android.mayojava.trivago.popularmovies.di.FragmentScoped;
import com.android.mayojava.trivago.search.SearchActivity;

import dagger.Component;

/**
 *
 */
@Component(modules = {SearchMoviePresenterModule.class}, dependencies = {ApiComponent.class})
@FragmentScoped
public interface SearchMovieComponent {
    void injectSearchMoviesActivity(SearchActivity searchActivity);
}
