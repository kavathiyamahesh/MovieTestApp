package com.android.mayojava.movies.dagger.components;

import android.content.Context;

import com.android.mayojava.movies.MoviesApplication;
import com.android.mayojava.movies.dagger.modules.ApplicationModule;
import com.android.mayojava.movies.dagger.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * application component
 */
@Component(modules = {ApplicationModule.class, NetworkModule.class})
@Singleton
public interface ApplicationComponent {
    //Exposes application to dependent component
    MoviesApplication getApplication();

    //Exposes retrofit to dependent component
    Retrofit getRetrofit();

    Context getContext();
}
