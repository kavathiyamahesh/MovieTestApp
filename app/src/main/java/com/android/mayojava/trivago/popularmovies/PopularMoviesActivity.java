package com.android.mayojava.trivago.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;

import com.android.mayojava.trivago.BaseActivity;
import com.android.mayojava.trivago.R;
import com.android.mayojava.trivago.TrivagoMoviesApplication;
import com.android.mayojava.trivago.dagger.components.ApiComponent;
import com.android.mayojava.trivago.dagger.components.DaggerApiComponent;
import com.android.mayojava.trivago.dagger.modules.ApiModule;
import com.android.mayojava.trivago.popularmovies.di.ActivityPresenterModule;
import com.android.mayojava.trivago.popularmovies.di.DaggerActivityComponent;
import com.android.mayojava.trivago.search.SearchActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Popular movies activity
 */
public class PopularMoviesActivity extends BaseActivity {
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Inject PopularMoviesPresenter mPopularMoviesPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);

        ButterKnife.bind(this);

        mToolbar.setTitle(getString(R.string.text_popular_movie));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToolbar() {
        mToolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_up_off));
    }

    public void hideToolbar() {
        mToolbar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate_up_on));
    }
}
