package com.android.mayojava.trivago.repository;

import com.android.mayojava.trivago.repository.api.MoviesService;
import com.android.mayojava.trivago.repository.datasource.MovieDataStoreFactory;
import com.android.mayojava.trivago.repository.datasource.MoviesRemoteDataStore;
import com.android.mayojava.trivago.repository.models.PopularMovie;
import com.android.mayojava.trivago.repository.models.search.SearchResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 *  movie data repository test
 */
public class MovieDataRepositoryTest extends ApplicationTestCase {

    private MovieDataRepository movieDataRepository;

    @Mock
    private MovieDataStoreFactory movieDataStoreFactory;
    @Mock
    private MoviesRepository moviesRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movieDataRepository = new MovieDataRepository(movieDataStoreFactory);

        given(movieDataStoreFactory.createCloudMoviesRepository()).willReturn(moviesRepository);
        given(movieDataStoreFactory.createMoviesRepository()).willReturn(moviesRepository);
    }

    @Test
    public void testGetPopularMoviesList() {
        String page = "1", limit = "10", extended = "full,images";

        List<PopularMovie> popularMovieList = new ArrayList<>();
        popularMovieList.add(new PopularMovie());

        given(moviesRepository.popularMovies(limit, page, extended)).
                willReturn(Observable.just(popularMovieList));


        movieDataRepository.popularMovies(limit, page, extended);

        verify(movieDataStoreFactory).createMoviesRepository();
        verify(moviesRepository).popularMovies(limit, page, extended);
    }

    @Test
    public void testGetSearchResult() {
        String query = "tron", page = "1", limit = "10", extended = "full,images";

        List<SearchResult> searchResults = new ArrayList<>();
        searchResults.add(new SearchResult());

        given(moviesRepository.searchMovies(query, page, limit, extended))
                .willReturn(Observable.just(searchResults));

        movieDataRepository.searchMovies(query, page, limit, extended);

        verify(movieDataStoreFactory).createCloudMoviesRepository();
        verify(moviesRepository).searchMovies(query, page, limit, extended);
    }
}
