package com.android.mayojava.trivago.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.mayojava.trivago.R;
import com.android.mayojava.trivago.TrivagoMoviesApplication;
import com.android.mayojava.trivago.base.BaseActivity;
import com.android.mayojava.trivago.dagger.components.ApiComponent;
import com.android.mayojava.trivago.dagger.components.DaggerApiComponent;
import com.android.mayojava.trivago.dagger.modules.ApiModule;
import com.android.mayojava.trivago.popularmovies.di.ActivityPresenterModule;
import com.android.mayojava.trivago.popularmovies.di.DaggerActivityComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Popular movies activity
 */
public class PopularMoviesActivity extends BaseActivity {

    @Inject PopularMoviesPresenter mPopularMoviesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);

        ButterKnife.bind(this);


        PopularMoviesFragment fragment = (PopularMoviesFragment)getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (savedInstanceState == null || fragment == null) {
            fragment = (PopularMoviesFragment)PopularMoviesFragment.newInstance(null);
            startFragment(fragment, R.id.container);
        }


        //create the presenter
        ApiComponent apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .applicationComponent(((TrivagoMoviesApplication)getApplication()).getBaseComponent())
                .build();
        DaggerActivityComponent.builder()
                .activityPresenterModule(new ActivityPresenterModule(fragment))
                .apiComponent(apiComponent)
                .build().injectPopularMoviesActivity(this);
    }
}
