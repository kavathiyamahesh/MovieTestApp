package com.android.mayojava.trivago.repository.datasource;

import android.content.Context;

import com.android.mayojava.trivago.repository.ApplicationTestCase;
import com.android.mayojava.trivago.repository.MoviesRepository;
import com.android.mayojava.trivago.repository.api.MoviesService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Movie data store factory test
 */
public class MovieDataStoreFactoryTest extends ApplicationTestCase {
    private MovieDataStoreFactory movieDataStoreFactory;

    @Mock
    private Context context;
    @Mock
    private MoviesService moviesService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movieDataStoreFactory = new MovieDataStoreFactory(context, moviesService);
    }

    @Test
    public void testCreateCloudMoviesRepository() {
        MoviesRepository moviesRemoteDataStore = movieDataStoreFactory.createCloudMoviesRepository();

        assertThat(moviesRemoteDataStore, is(notNullValue()));
        assertThat(moviesRemoteDataStore, is(instanceOf(MoviesRemoteDataStore.class)));
    }
}
