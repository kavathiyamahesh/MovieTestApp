package com.android.mayojava.movies.search.di;

import com.android.mayojava.movies.dagger.components.ApiComponent;
import com.android.mayojava.movies.popularmovies.di.FragmentScoped;
import com.android.mayojava.movies.search.SearchActivity;

import dagger.Component;

/**
 *
 */
@Component(modules = {SearchMoviePresenterModule.class}, dependencies = {ApiComponent.class})
@FragmentScoped
public interface SearchMovieComponent {
    void injectSearchMoviesActivity(SearchActivity searchActivity);
}
