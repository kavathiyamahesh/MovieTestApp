package com.android.mayojava.trivago.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.android.mayojava.trivago.BaseActivity;
import com.android.mayojava.trivago.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Popular movies activity
 */
public class PopularMoviesActivity extends BaseActivity {
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            startFragment(PopularMoviesFragment.newInstance(null), R.id.container);
        }
    }
}
