package com.android.mayojava.trivago.search;

import android.support.annotation.NonNull;

import com.android.mayojava.trivago.repository.MovieDataRepository;

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

        mSubscription = mMovieDataRepository.searchMovies("query", String.valueOf(page),
                "10", "full,images")

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchResults -> {
                    if (mView.isSearchResultEmpty()) {
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


}
