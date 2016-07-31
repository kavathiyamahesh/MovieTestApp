package com.android.mayojava.trivago;

import android.app.Application;

import com.android.mayojava.trivago.dagger.components.ApplicationComponent;
import com.android.mayojava.trivago.dagger.components.DaggerApplicationComponent;
import com.android.mayojava.trivago.dagger.modules.ApplicationModule;
import com.android.mayojava.trivago.dagger.modules.NetworkModule;

/**
 *
 */
public class TrivagoMoviesApplication extends Application {
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
