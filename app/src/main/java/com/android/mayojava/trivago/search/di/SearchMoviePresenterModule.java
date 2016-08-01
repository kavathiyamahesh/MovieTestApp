package com.android.mayojava.trivago.search.di;

import com.android.mayojava.trivago.popularmovies.di.FragmentScoped;
import com.android.mayojava.trivago.search.SearchMoviesContract;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class SearchMoviePresenterModule {
    private final SearchMoviesContract.View mView;

    public SearchMoviePresenterModule(SearchMoviesContract.View view) {
        this.mView = view;
    }

    @Provides
    @FragmentScoped
    SearchMoviesContract.View providesSearchMoviesView() {
        return mView;
    }
}
