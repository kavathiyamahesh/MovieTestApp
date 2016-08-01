package com.android.mayojava.trivago.popularmovies;


import com.android.mayojava.trivago.BasePresenter;
import com.android.mayojava.trivago.BaseView;
import com.android.mayojava.trivago.repository.models.PopularMovie;

import java.util.List;

/**
 * specifies the contract between the view and the presenter
 */

public interface PopularMoviesContract {

    interface View extends BaseView<Presenter> {
        void showProgressBar();

        void hideProgressBar();

        void updateRecyclerAdapter(List<PopularMovie> popularMovies);

        void showLoadingMoreProgress();

        void hideShowLoadingMoreProgress();

        boolean isMovieListEmpty();

        void showToast(String message);
    }

    interface Presenter extends BasePresenter {

        void loadPopularMovies(int page);

        void onListEndReached(int newPage);

        boolean isLoading();

        void onDestroy();
    }

}
