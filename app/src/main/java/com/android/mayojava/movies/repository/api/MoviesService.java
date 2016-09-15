package com.android.mayojava.movies.repository.api;

import com.android.mayojava.movies.repository.models.PopularMovie;
import com.android.mayojava.movies.repository.models.search.SearchResult;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Movie service API
 */
@Singleton
public class MoviesService {
    private MoviesApi moviesApi;

    @Inject
    public MoviesService(Retrofit retrofit) {
        moviesApi = retrofit.create(MoviesApi.class);
    }

    public MoviesApi getMoviesApi() {
        return moviesApi;
    }

    public interface MoviesApi {
        @GET("movies/popular?")
        Observable<List<PopularMovie>> getPopularMovies(@Query("limit") String limit,
                         @Query("page") String page, @Query("extended") String extended);

        @GET("search/movie?")
        Observable<List<SearchResult>> getMovieSearchResult(@Query("query") String query,
                                                            @Query("page") String page,
                                                            @Query("limit") String limit,
                                                            @Query("extended") String extended);

    }
}
