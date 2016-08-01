package com.android.mayojava.trivago.moviedetails;

import com.android.mayojava.trivago.base.BasePresenter;
import com.android.mayojava.trivago.base.BaseView;

/**
 *  contract between details view and presenter
 */
public interface MovieDetailsContract {

    interface View extends BaseView<Presenter> {
        void loadMoviePosterImage(String url);

        void setMovieTitle(String title);

        void setMovieOverview(String overview);

        void setMovieTagline(String tagline);

        void showRatings(float ratings);

        void setMovieTrailerUrl(String url);

        void setYear(String year);

        void setGenre(String genre);
    }

    interface Presenter extends BasePresenter {
        void loadPosterImage(String url);

        void setTitle(String title);

        void setOverview(String overview);

        void setTagline(String tagline);

        void setRatings(Double ratings);

        void setTrailer(String trailerUrl);

        void setYear(String year);

        void setGenre(String genre);
    }
}
