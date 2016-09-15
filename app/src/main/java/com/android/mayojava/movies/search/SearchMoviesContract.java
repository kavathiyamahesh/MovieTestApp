package com.android.mayojava.movies.search;

import com.android.mayojava.movies.base.BasePresenter;
import com.android.mayojava.movies.base.BaseView;
import com.android.mayojava.movies.repository.models.search.SearchResult;

import java.util.List;

/**
 *
 */
public interface SearchMoviesContract {
    interface View extends BaseView<Presenter> {
        void setSearchResult(List<SearchResult> searchResults);

        void updateResultList(List<SearchResult> searchResults);

        void showLoadingProgress();

        void hideLoadingProgress();

        void showLoadMoreProgress();

        void hideShowMoreProgress();

        boolean isSearchResultEmpty();

        void clearSearchResultAdapter();

        void showToast(String message);
    }

    interface Presenter extends BasePresenter {
        void querySearchApi(String searchTerm, int page);

        void onDestroy();

        boolean isLoading();

        void onListEndReached(int newPage, String searchTerm);

        void clearSearchResult();
    }
}
