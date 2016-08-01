package com.android.mayojava.trivago.moviedetails;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.mayojava.trivago.base.BaseActivity;
import com.android.mayojava.trivago.R;
import com.android.mayojava.trivago.TrivagoMoviesApplication;
import com.android.mayojava.trivago.dagger.components.ApiComponent;
import com.android.mayojava.trivago.dagger.components.DaggerApiComponent;
import com.android.mayojava.trivago.dagger.modules.ApiModule;
import com.android.mayojava.trivago.moviedetails.di.DaggerMovieDetailsComponent;
import com.android.mayojava.trivago.moviedetails.di.MovieDetailsModule;
import com.android.mayojava.trivago.moviedetails.di.MovieDetailsPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Movie details activity
 */
public class MovieDetailsActivity extends BaseActivity {

    @Inject MovieDetailsPresenter mMovieDetailsPresenter;

    /**
     * movie details bundle params
     */
    public static final String ARG_MOVIE_POSTER = "movie_poster";
    public static final String ARG_MOVIE_TITLE = "movie_title";
    public static final String ARG_TAGLINE= "header";
    public static final String ARG_OVERVIEW = "overview";
    public static final String ARG_RATINGS = "ratings";
    public static final String ARG_TRAILER = "trailer";
    public static final String ARG_GENRE = "genre";
    public static final String ARG_YEAR = "year";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        MovieDetailsFragment fragment = (MovieDetailsFragment)getSupportFragmentManager().
                findFragmentById(R.id.container);

        if (savedInstanceState == null || fragment == null) {
            fragment = (MovieDetailsFragment) MovieDetailsFragment.newInstance(getIntent().
                    getExtras());
            startFragment(fragment, R.id.container);
        }

        ApiComponent apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .applicationComponent(((TrivagoMoviesApplication)getApplication()).
                        getBaseComponent())
                .build();
        DaggerMovieDetailsComponent.builder()
                .apiComponent(apiComponent)
                .movieDetailsModule(new MovieDetailsModule(fragment))
                .build().injectMovieDetailsActivity(this);
    }

}
