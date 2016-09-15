package com.android.mayojava.movies.dagger.modules;

import android.content.Context;

import com.android.mayojava.movies.MoviesApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application module
 */
@Module
public class ApplicationModule {
    private static MoviesApplication sApplication;

    public ApplicationModule(MoviesApplication application) {
        sApplication = application;
    }

    @Singleton
    @Provides
    MoviesApplication providesApplication() {
        return sApplication;
    }

    @Singleton
    @Provides
    Context providesContext() {
        return sApplication;
    }
}
