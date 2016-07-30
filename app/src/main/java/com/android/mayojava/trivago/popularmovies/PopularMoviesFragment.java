package com.android.mayojava.trivago.popularmovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.mayojava.trivago.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Popular movies fragment
 */
public class PopularMoviesFragment extends Fragment {
    @BindView(R.id.recycler_view_popular_movies) RecyclerView mPopularMovies;
    @BindView(R.id.progress_bar_indeterminate) ProgressBar mProgressBarIndeterminate;

    private PopularMoviesContract.Presenter presenter;

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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
