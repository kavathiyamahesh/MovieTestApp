package com.android.mayojava.trivago.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.mayojava.trivago.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class SearchFragment extends Fragment {
    @BindView(R.id.toolbar_search_movies) Toolbar mToolbar;
    @BindView(R.id.edit_text_search_movies) EditText mSearchEditText;
    @BindView(R.id.recycler_view_search_result) RecyclerView mRecyclerViewSearchResult;


    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new SearchFragment();
        if (args == null) {
            args = new Bundle();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_movies, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getHostActivity().getMenuInflater().inflate(R.menu.menu_movie_clear_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        getHostActivity().setSupportActionBar(mToolbar);
        ActionBar actionBar = getHostActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private SearchActivity getHostActivity() {
        return (SearchActivity)getActivity();
    }
}
