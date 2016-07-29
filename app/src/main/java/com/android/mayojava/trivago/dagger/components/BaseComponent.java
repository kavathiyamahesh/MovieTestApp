package com.android.mayojava.trivago.dagger.components;

import android.support.v4.view.ViewCompat;

import com.android.mayojava.trivago.dagger.modules.NetworkModule;
import com.android.mayojava.trivago.dagger.scope.CustomScope;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Base component
 */
@Component(modules = {NetworkModule.class}, dependencies = {ApplicationComponent.class})
@CustomScope
public interface BaseComponent {
    Retrofit getRetrofit();
}
