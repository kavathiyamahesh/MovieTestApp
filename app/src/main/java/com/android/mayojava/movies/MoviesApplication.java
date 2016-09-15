package com.android.mayojava.movies;

import android.app.Application;

import com.android.mayojava.movies.dagger.components.ApplicationComponent;
import com.android.mayojava.movies.dagger.components.DaggerApplicationComponent;
import com.android.mayojava.movies.dagger.modules.ApplicationModule;
import com.android.mayojava.movies.dagger.modules.NetworkModule;

/**
 *
 */
public class MoviesApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent =
                DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                        .networkModule(new NetworkModule(BuildConfig.api_base_url))
                    .build();
    }

    public ApplicationComponent getBaseComponent() {
        return mApplicationComponent;
    }
}
