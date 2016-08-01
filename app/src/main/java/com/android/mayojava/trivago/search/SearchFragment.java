package com.android.mayojava.trivago.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.view.menu.MenuPopupHelper;
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
import com.android.mayojava.trivago.RecyclerViewItemClickListener;
import com.android.mayojava.trivago.custom.VerticalSpaceItemDecoration;
import com.android.mayojava.trivago.moviedetails.MovieDetailsActivity;
import com.android.mayojava.trivago.repository.models.search.SearchResult;
import com.android.mayojava.trivago.search.adapter.SearchMoviesRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class SearchFragment extends Fragment implements SearchMoviesContract.View,
        RecyclerViewItemClickListener {
    @BindView(R.id.toolbar_search_movies) Toolbar mToolbar;
    @BindView(R.id.edit_text_search_movies) EditText mSearchEditText;
    @BindView(R.id.recycler_view_search_result) RecyclerView mRecyclerViewSearchResult;
    @BindView(R.id.progress_bar_loading_more_movies) ProgressBar mLoadMoreResultIndeterminateProgress;
    @BindView(R.id.progress_bar_search_result) ProgressBar mLoadingSearchResultProgressBar;

    private SearchMoviesContract.Presenter mPresenter;
    private SearchMoviesRecyclerAdapter mRecyclerAdapter;
    private final int VERTICAL_ITEM_SPACE = 48;
    private String mSearchTerm;

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
        switch (item.getItemId()) {
            case android.R.id.home:
                getHostActivity().finish();
                break;
            case R.id.menu_search_clear:
                mSearchEditText.setText("");
                mPresenter.clearSearchResult();
                break;
        }
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
        mRecyclerViewSearchResult.addOnScrollListener(mCustomScrollListener);
    }

    @Override
    public void setSearchResult(List<SearchResult> searchResults) {
        if (mRecyclerAdapter == null) {
            mRecyclerAdapter = new SearchMoviesRecyclerAdapter(getContext(), searchResults);
            mRecyclerViewSearchResult.setAdapter(mRecyclerAdapter);
            mRecyclerAdapter.setRecyclerViewItemClickListener(this);
        } else {
            mRecyclerAdapter.updateSearchResponse(searchResults);
        }

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
    public void showLoadMoreProgress() {
        mLoadMoreResultIndeterminateProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideShowMoreProgress() {
        mLoadMoreResultIndeterminateProgress.setVisibility(View.GONE);
    }

    @Override
    public boolean isSearchResultEmpty() {
        return mRecyclerAdapter == null || mRecyclerAdapter.getItemCount() == 0;
    }

    @Override
    public void clearSearchResultAdapter() {
        mRecyclerAdapter.clearContent();
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

    private RecyclerView.OnScrollListener mCustomScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleCount = mRecyclerViewSearchResult.getLayoutManager().getChildCount();
            int totalItemCount = mRecyclerViewSearchResult.getLayoutManager().getItemCount();
            int pastVisibleItems = ((LinearLayoutManager) mRecyclerViewSearchResult.
                    getLayoutManager()).findFirstVisibleItemPosition();

            if (visibleCount + pastVisibleItems >= totalItemCount && !mPresenter.isLoading()) {
                int newPage = mRecyclerAdapter.getItemCount()/10 + 1;
                mPresenter.onListEndReached(newPage, mSearchTerm);
            }
        }
    };


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
            mSearchTerm = s.toString();
            if (mSearchTerm.trim().length() > 0) {
                mPresenter.querySearchApi(mSearchTerm, 1);
            } else {
                mPresenter.clearSearchResult();
            }

        }
    };

    @Override
    public void onItemClick(View v, int position, float x, float y) {
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);

        SearchResult result = mRecyclerAdapter.getItemAt(position);
        intent.putExtras(createDetailsBundle(result));
        startActivity(intent);

    }

    private Bundle createDetailsBundle(SearchResult movie) {
        Bundle bundle = new Bundle();
        bundle.putString(MovieDetailsActivity.ARG_MOVIE_POSTER, movie.getMovie().getImages().
                getPoster().getFull());
        bundle.putString(MovieDetailsActivity.ARG_MOVIE_TITLE, movie.getMovie().getTitle());
        bundle.putString(MovieDetailsActivity.ARG_OVERVIEW, movie.getMovie().getOverview());
        bundle.putDouble(MovieDetailsActivity.ARG_RATINGS, movie.getMovie().getRating());
        bundle.putString(MovieDetailsActivity.ARG_TAGLINE, movie.getMovie().getTagline());
        bundle.putString(MovieDetailsActivity.ARG_TRAILER, movie.getMovie().getTrailer());
        bundle.putString(MovieDetailsActivity.ARG_GENRE, movie.getMovie().getGenres().toString());
        bundle.putString(MovieDetailsActivity.ARG_YEAR, movie.getMovie().getReleased());
        return bundle;
    }
}
