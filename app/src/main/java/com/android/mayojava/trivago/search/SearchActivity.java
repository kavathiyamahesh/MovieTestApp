package com.android.mayojava.trivago.search;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.mayojava.trivago.base.BaseActivity;
import com.android.mayojava.trivago.R;
import com.android.mayojava.trivago.TrivagoMoviesApplication;
import com.android.mayojava.trivago.dagger.components.ApiComponent;
import com.android.mayojava.trivago.dagger.components.DaggerApiComponent;
import com.android.mayojava.trivago.dagger.modules.ApiModule;
import com.android.mayojava.trivago.search.di.DaggerSearchMovieComponent;
import com.android.mayojava.trivago.search.di.SearchMoviePresenterModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 *
 */
public class SearchActivity extends BaseActivity {
    @Inject SearchMoviesPresenter mSearchMoviesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        SearchFragment fragment = (SearchFragment)getSupportFragmentManager().
                findFragmentById(R.id.container);

        if (savedInstanceState == null || fragment == null) {
            fragment = (SearchFragment)SearchFragment.newInstance(null);
            startFragment(fragment, R.id.container);
        }

        //create the presenter
        ApiComponent apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .applicationComponent(((TrivagoMoviesApplication)getApplication()).getBaseComponent())
                .build();
        DaggerSearchMovieComponent.builder()
                .searchMoviePresenterModule(new SearchMoviePresenterModule(fragment))
                .apiComponent(apiComponent)
                .build().injectSearchMoviesActivity(this);
    }
}
