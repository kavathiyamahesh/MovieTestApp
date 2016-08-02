package com.android.mayojava.trivago.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.mayojava.trivago.R;
import com.android.mayojava.trivago.RecyclerViewItemClickListener;
import com.android.mayojava.trivago.custom.InsetItemDecoration;
import com.android.mayojava.trivago.moviedetails.MovieDetailsActivity;
import com.android.mayojava.trivago.popularmovies.adapter.PopularMoviesRecyclerAdapter;
import com.android.mayojava.trivago.repository.models.PopularMovie;
import com.android.mayojava.trivago.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Popular movies fragment
 */
public class PopularMoviesFragment extends Fragment implements PopularMoviesContract.View,
        RecyclerViewItemClickListener {

    @BindView(R.id.recycler_view_popular_movies) RecyclerView mPopularMoviesRecyclerView;
    @BindView(R.id.progress_bar_indeterminate) ProgressBar mProgressBarIndeterminate;
    @BindView(R.id.progress_bar_loading_more_movies) ProgressBar mLoadMoreIndeterminateProgressBar;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    private PopularMoviesContract.Presenter mPresenter;

    private PopularMoviesRecyclerAdapter mRecyclerAdapter;
    private List<PopularMovie> mPopularMovieList;

    /**
     * static function to return fragment instance
     *
     * @param args - bundle parameters
     * @return - Fragment
     */
    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new PopularMoviesFragment();

        if (args == null) {
            args = new Bundle();
        }

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPopularMovieList = new ArrayList<>();
        mRecyclerAdapter = new PopularMoviesRecyclerAdapter(getContext(), mPopularMovieList);
        mRecyclerAdapter.setRecyclerViewItemClickListener(this);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        ButterKnife.bind(this, view);

        initializeRecyclerView();

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupToolbar();

        //load popular movies
        mPresenter.loadPopularMovies(1);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void setPresenter(PopularMoviesContract.Presenter presenter) {
        if (presenter != null) {
            mPresenter = presenter;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getHostActivity().finish();
                break;
            case R.id.menu_search:
                startActivity(new Intent(getHostActivity(), SearchActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showProgressBar() {
        mProgressBarIndeterminate.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBarIndeterminate.setVisibility(View.GONE);
    }

    @Override
    public void updateRecyclerAdapter(List<PopularMovie> popularMovies) {
        mPopularMovieList.addAll(popularMovies);
        mRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingMoreProgress() {
        mLoadMoreIndeterminateProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideShowLoadingMoreProgress() {
        mLoadMoreIndeterminateProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isMovieListEmpty() {
        return mPopularMovieList.size() == 0;
    }

    private void initializeRecyclerView() {
        mPopularMoviesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mPopularMoviesRecyclerView.setAdapter(mRecyclerAdapter);
        mPopularMoviesRecyclerView.addOnScrollListener(mRecyclerScrollListener);
        mPopularMoviesRecyclerView.addItemDecoration(new InsetItemDecoration(getContext()));
    }

    private RecyclerView.OnScrollListener mRecyclerScrollListener = new RecyclerView.OnScrollListener() {
        private boolean scrollDirectionFlag;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = mPopularMoviesRecyclerView.getLayoutManager().getChildCount();
            int totalItemCount = mPopularMoviesRecyclerView.getLayoutManager().getItemCount();
            int pastVisibleItems = ((GridLayoutManager) mPopularMoviesRecyclerView.
                    getLayoutManager()).findFirstVisibleItemPosition();

            if (visibleItemCount + pastVisibleItems >= totalItemCount && !mPresenter.isLoading()) {
                int newPage = mRecyclerAdapter.getItemCount()/10 + 1;
                mPresenter.onListEndReached(newPage);
            }
        }
    };

    private PopularMoviesActivity getHostActivity() {
        return (PopularMoviesActivity)getActivity();
    }


    @Override
    public void onItemClick(View v, int position, float x, float y) {
        Intent intent = new Intent(getContext(), MovieDetailsActivity.class);

        PopularMovie popularMovie = mRecyclerAdapter.getItemAt(position);

        intent.putExtras(createDetailsBundle(popularMovie));

        startActivity(intent);
    }

    private void setupToolbar() {
        getHostActivity().setSupportActionBar(mToolbar);
        ActionBar actionBar = getHostActivity().getSupportActionBar();

        mToolbar.setTitle(getString(R.string.text_popular_movie));

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    private Bundle createDetailsBundle(PopularMovie popularMovie) {
        Bundle bundle = new Bundle();
        bundle.putString(MovieDetailsActivity.ARG_MOVIE_POSTER, popularMovie.getImages().
                getPoster().getFull());
        bundle.putString(MovieDetailsActivity.ARG_MOVIE_TITLE, popularMovie.getTitle());
        bundle.putString(MovieDetailsActivity.ARG_OVERVIEW, popularMovie.getOverview());
        bundle.putDouble(MovieDetailsActivity.ARG_RATINGS, popularMovie.getRating());
        bundle.putString(MovieDetailsActivity.ARG_TAGLINE, popularMovie.getTagline());
        bundle.putString(MovieDetailsActivity.ARG_TRAILER, popularMovie.getTrailer());
        bundle.putString(MovieDetailsActivity.ARG_GENRE, popularMovie.getGenres().toString());
        bundle.putString(MovieDetailsActivity.ARG_YEAR, popularMovie.getReleased());
        return bundle;
    }
}
