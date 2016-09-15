package com.android.mayojava.movies.search;

import android.support.annotation.NonNull;

import com.android.mayojava.movies.repository.MovieDataRepository;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Search movies presenter
 */
public class SearchMoviesPresenter implements SearchMoviesContract.Presenter {
    @NonNull
    private final SearchMoviesContract.View mView;
    @NonNull
    private final MovieDataRepository mMovieDataRepository;

    private Subscription mSubscription;
    private boolean isLoading = false;

    @Inject
    public SearchMoviesPresenter(SearchMoviesContract.View view,
                                 MovieDataRepository movieDataRepository) {
        this.mView = view;
        this.mMovieDataRepository = movieDataRepository;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void querySearchApi(String searchTerm, int page) {
        if (mView.isSearchResultEmpty()) {
            mView.showLoadingProgress();
        }

        mSubscription = mMovieDataRepository.searchMovies(searchTerm, String.valueOf(page),
                "10", "full,images")

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResults -> {
                    if (mView.isSearchResultEmpty() || page == 1) {
                        mView.setSearchResult(searchResults);
                    } else {
                        mView.updateResultList(searchResults);
                    }
                },
                        error -> hideProgressLoaders(), () -> hideProgressLoaders() );

    }

    @Override
    public void hideProgressLoaders() {
        mView.hideLoadingProgress();
        mView.hideShowMoreProgress();
        isLoading = false;
    }

    @Override
    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void onListEndReached(int newPage, String searchTerm) {
        mView.showLoadMoreProgress();
        querySearchApi(searchTerm, newPage);
        isLoading = true;
    }

    @Override
    public void clearSearchResult() {
        mView.clearSearchResultAdapter();
    }
}
