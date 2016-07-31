package com.android.mayojava.trivago.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.mayojava.trivago.R;
import com.android.mayojava.trivago.custom.InsetItemDecoration;
import com.android.mayojava.trivago.popularmovies.adapter.PopularMoviesRecyclerAdapter;
import com.android.mayojava.trivago.repository.models.PopularMovie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Popular movies fragment
 */
public class PopularMoviesFragment extends Fragment implements PopularMoviesContract.View {
    @BindView(R.id.recycler_view_popular_movies) RecyclerView mPopularMoviesRecyclerView;
    @BindView(R.id.progress_bar_indeterminate) ProgressBar mProgressBarIndeterminate;
    @BindView(R.id.progress_bar_loading_more_movies) ProgressBar mLoadMoreIndeterminateProgressBar;

    PopularMoviesContract.Presenter mPresenter;

    private int page = 1;
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

        if (args != null) {
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

        //load popular movies
        mPresenter.loadPopularMovies(page);
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
                mPresenter.onListEndReached(++page);
            }

            //check if scrolling up
            if (dy > 10) {
                if (!scrollDirectionFlag) {
                    getHostActivity().showToolbar();
                    scrollDirectionFlag = true;
                }
            } else if (dy < - 10) {
                if (scrollDirectionFlag) {
                    getHostActivity().hideToolbar();
                    scrollDirectionFlag = false;
                }
            }
        }
    };

    private PopularMoviesActivity getHostActivity() {
        return (PopularMoviesActivity)getActivity();
    }
}
