package com.android.mayojava.trivago.repository.datasource;

import com.android.mayojava.trivago.repository.MoviesRepository;
import com.android.mayojava.trivago.repository.models.PopularMovie;
import com.android.mayojava.trivago.repository.models.search.SearchResult;

import java.util.List;

import rx.Observable;

/**
 * Created by mayowa.adegeye on 30/07/2016.
 */
public class MoviesLocalDataStore implements MoviesRepository {
    @Override
    public Observable<List<PopularMovie>> popularMovies(String limit, String page,
                                                        String extended) {
        return null;
    }

    @Override
    public Observable<List<SearchResult>> searchMovies(String query, String page,
                                                       String limit, String extended) {
        return null;
    }
}
