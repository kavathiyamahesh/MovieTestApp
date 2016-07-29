package com.android.mayojava.trivago.dagger.modules;

import com.android.mayojava.trivago.TrivagoMoviesApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application module
 */
@Module
public class ApplicationModule {
    private static TrivagoMoviesApplication sApplication;

    public ApplicationModule(TrivagoMoviesApplication application) {
        sApplication = application;
    }

    @Singleton
    @Provides
    TrivagoMoviesApplication providesApplication() {
        return sApplication;
    }
}
