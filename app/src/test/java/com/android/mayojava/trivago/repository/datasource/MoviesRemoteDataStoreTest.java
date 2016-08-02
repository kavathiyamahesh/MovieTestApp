package com.android.mayojava.trivago.repository.datasource;

import com.android.mayojava.trivago.repository.ApplicationTestCase;
import com.android.mayojava.trivago.repository.api.MoviesService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Movies remote data store test
 */
public class MoviesRemoteDataStoreTest extends ApplicationTestCase {

    @Mock private MoviesService.MoviesApi moviesApi;

    private MoviesRemoteDataStore moviesRemoteDataStore;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        moviesRemoteDataStore = new MoviesRemoteDataStore(moviesApi);
    }

    @Test
    public void testGetListOfPopularMoviesFromApi() {
        moviesRemoteDataStore.popularMovies("10", "1", "full,images");
        verify(moviesApi).getPopularMovies("10", "1", "full,images");
    }

    @Test
    public void testGetSearchResultFromMoviesApi() {
        moviesRemoteDataStore.searchMovies("tron", "1", "10", "full,images");
        verify(moviesApi).getMovieSearchResult("tron", "1", "10", "full,images");
    }
}
