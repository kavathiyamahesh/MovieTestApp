package com.android.mayojava.trivago.popularmovies;

import android.support.annotation.NonNull;

import com.android.mayojava.trivago.repository.MovieDataRepository;
import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Popular movies presenter
 */
public class PopularMoviesPresenter implements PopularMoviesContract.Presenter {
    @NonNull
    private final PopularMoviesContract.View mView;
    @NonNull
    private final MovieDataRepository mMovieDataRepository;

    private final String limit = "10";
    private final String extended = "full,images";
    private boolean isLoading = false;

    Subscription mSubscription;

    @Inject
    public PopularMoviesPresenter (PopularMoviesContract.View view,
                                   MovieDataRepository movieRepository) {
        this.mView = view;
        this.mMovieDataRepository = movieRepository;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void loadPopularMovies(int page) {
        if (mView.isMovieListEmpty()) {
            mView.showProgressBar();
        }

        mSubscription = mMovieDataRepository.popularMovies(limit, String.valueOf(page), extended)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popularMovies -> {mView.updateRecyclerAdapter(popularMovies);},
                        error->{ hideProgressLoaders(); }, ()->{ hideProgressLoaders(); });
    }

    @Override
    public void onListEndReached(int newPage) {
        mView.showLoadingMoreProgress();
        loadPopularMovies(newPage);
        isLoading = true;
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void onDestroy() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private void hideProgressLoaders() {
        mView.hideProgressBar();
        mView.hideShowLoadingMoreProgress();
        isLoading = false;
    }

}
