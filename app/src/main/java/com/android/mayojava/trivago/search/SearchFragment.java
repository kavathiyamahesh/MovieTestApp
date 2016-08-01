package com.android.mayojava.trivago.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.mayojava.trivago.R;
import com.android.mayojava.trivago.custom.VerticalSpaceItemDecoration;
import com.android.mayojava.trivago.repository.models.search.SearchResult;
import com.android.mayojava.trivago.search.adapter.SearchMoviesRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class SearchFragment extends Fragment implements SearchMoviesContract.View {
    @BindView(R.id.toolbar_search_movies) Toolbar mToolbar;
    @BindView(R.id.edit_text_search_movies) EditText mSearchEditText;
    @BindView(R.id.recycler_view_search_result) RecyclerView mRecyclerViewSearchResult;
    @BindView(R.id.progress_bar_loading_more_movies) ProgressBar mLoadMoreResultIndeterminateProgress;
    @BindView(R.id.progress_bar_search_result) ProgressBar mLoadingSearchResultProgressBar;

    private SearchMoviesContract.Presenter mPresenter;
    private SearchMoviesRecyclerAdapter mRecyclerAdapter;
    private int page  = 1;
    private final int VERTICAL_ITEM_SPACE = 48;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_movies, container, false);
        ButterKnife.bind(this, view);

        initializeRecyclerView();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar();
        mSearchEditText.addTextChangedListener(mSearchTextTextWatcher);

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

    @Override
    public void setPresenter(SearchMoviesContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    private void initializeRecyclerView() {
        mRecyclerViewSearchResult.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerViewSearchResult.addItemDecoration(
                new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
    }

    @Override
    public void setSearchResult(List<SearchResult> searchResults) {
        mRecyclerAdapter = new SearchMoviesRecyclerAdapter(getContext(), searchResults);
        mRecyclerViewSearchResult.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void updateResultList(List<SearchResult> searchResults) {
        mRecyclerAdapter.appendMatchingSearchResult(searchResults);
    }

    @Override
    public void showLoadingProgress() {
        mLoadingSearchResultProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        mLoadingSearchResultProgressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean isSearchResultEmpty() {
        return mRecyclerAdapter == null || mRecyclerAdapter.getItemCount() == 0;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * text watcher for search edit text
     */
    private TextWatcher mSearchTextTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mPresenter.querySearchApi(s.toString(), page);
        }
    };
}
