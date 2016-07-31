package com.android.mayojava.trivago.dagger.components;

import android.content.Context;

import com.android.mayojava.trivago.TrivagoMoviesApplication;
import com.android.mayojava.trivago.dagger.modules.ApplicationModule;
import com.android.mayojava.trivago.dagger.modules.NetworkModule;

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
    TrivagoMoviesApplication getApplication();

    //Exposes retrofit to dependent component
    Retrofit getRetrofit();

    Context getContext();
}
