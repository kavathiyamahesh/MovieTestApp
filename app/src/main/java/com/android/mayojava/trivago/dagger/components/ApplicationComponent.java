package com.android.mayojava.trivago.dagger.components;

import com.android.mayojava.trivago.TrivagoMoviesApplication;
import com.android.mayojava.trivago.dagger.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * application component
 */
@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {
    //Exposes application to dependent component
    TrivagoMoviesApplication getApplication();
}
