package com.android.mayojava.trivago.popularmovies;


import com.android.mayojava.trivago.BasePresenter;
import com.android.mayojava.trivago.BaseView;

/**
 * specifies the contract between the view and the presenter
 */

public interface PopularMoviesContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }

}
