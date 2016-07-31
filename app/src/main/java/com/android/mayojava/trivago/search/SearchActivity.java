package com.android.mayojava.trivago.search;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.mayojava.trivago.BaseActivity;
import com.android.mayojava.trivago.R;

import butterknife.ButterKnife;

/**
 *
 */
public class SearchActivity extends BaseActivity {
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
    }
}
