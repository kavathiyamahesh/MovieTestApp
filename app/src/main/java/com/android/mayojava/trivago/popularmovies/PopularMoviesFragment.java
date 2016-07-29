package com.android.mayojava.trivago.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Popular movies fragment
 */
public class PopularMoviesFragment extends Fragment {
    /**
     * static function to return fragment instance
     *
     * @param args
     * @return
     */
    public static Fragment newInstance(Bundle args) {
        Fragment fragment = new PopularMoviesFragment();

        if (args != null) {
            args = new Bundle();
        }

        fragment.setArguments(args);

        return fragment;
    }
}
