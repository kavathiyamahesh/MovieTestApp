package com.android.mayojava.trivago.repository;

import com.android.mayojava.trivago.repository.datasource.MovieDataStoreFactory;
import com.android.mayojava.trivago.repository.models.PopularMovie;
import com.android.mayojava.trivago.repository.models.search.SearchResult;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 *
 */
public class MovieDataRepository  {
    private final MovieDataStoreFactory movieDataStoreFactory;

    @Inject
    public MovieDataRepository(MovieDataStoreFactory movieDataStoreFactory ) {
        this.movieDataStoreFactory = movieDataStoreFactory;
    }

    public Observable<List<PopularMovie>> popularMovies(String limit, String page, String extended) {
        return this.movieDataStoreFactory.createMoviesRepository().popularMovies(limit, page, extended);
    }


    public Observable<List<SearchResult>> searchMovies(String query, String page,
                                                       String limit, String extended) {
        return movieDataStoreFactory.createCloudMoviesRepository().searchMovies(query, page,
                limit, extended);
    }
}
