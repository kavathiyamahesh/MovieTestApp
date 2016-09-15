package com.android.mayojava.movies.repository.datasource;

import android.content.Context;

import com.android.mayojava.movies.popularmovies.di.FragmentScoped;
import com.android.mayojava.movies.repository.MoviesRepository;
import com.android.mayojava.movies.repository.api.MoviesService;

import javax.inject.Inject;

/**
 * Data store factory which encapsulates the local and remote data
 * store
 */
@FragmentScoped
public class MovieDataStoreFactory {
    private final Context context;
    private final MoviesService mMovieService;

    @Inject
    public MovieDataStoreFactory(Context context, MoviesService movieService) {
        if (context == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }

        this.context = context;
        this.mMovieService = movieService;
    }

    /**
     * creates a cloud movies repository
     *
     * @return - MoviesRepository
     */
    public MoviesRepository createCloudMoviesRepository() {
        return new MoviesRemoteDataStore(mMovieService.getMoviesApi());
    }

    /**
     * creates a MoviesRepository that fetches from cloud or local store
     *
     */
    public MoviesRepository createMoviesRepository() {
        return new MoviesRemoteDataStore(mMovieService.getMoviesApi());
    }
}
