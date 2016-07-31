package com.android.mayojava.trivago.repository;

import com.android.mayojava.trivago.repository.models.PopularMovie;

import java.util.List;

import rx.Observable;

/**
 * interface that represents a repository for getting movies data
 */
public interface MoviesRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link PopularMovie}.
     */
    Observable<List<PopularMovie>> popularMovies(String limit, String page, String extended);

    /**
     * Get an {@link rx.Observable} which will emit a List of {@link PopularMovie}.
     */
    Observable<List<PopularMovie>> searchMovies(String query, String extended);
}
