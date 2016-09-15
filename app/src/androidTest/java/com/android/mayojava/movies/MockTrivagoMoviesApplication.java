package com.android.mayojava.movies;

import com.android.mayojava.movies.dagger.components.ApplicationComponent;

/**
 * Created by mayowa.adegeye on 02/08/2016.
 */
public class MockTrivagoMoviesApplication extends MoviesApplication {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public ApplicationComponent getBaseComponent() {
        return this.mApplicationComponent;
    }
}
