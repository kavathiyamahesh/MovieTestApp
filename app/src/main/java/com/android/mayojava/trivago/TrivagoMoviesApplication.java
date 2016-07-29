package com.android.mayojava.trivago;

import android.app.Application;

import com.android.mayojava.trivago.dagger.components.ApplicationComponent;
import com.android.mayojava.trivago.dagger.components.BaseComponent;
import com.android.mayojava.trivago.dagger.components.DaggerApplicationComponent;
import com.android.mayojava.trivago.dagger.components.DaggerBaseComponent;
import com.android.mayojava.trivago.dagger.modules.ApplicationModule;
import com.android.mayojava.trivago.dagger.modules.NetworkModule;

/**
 *
 */
public class TrivagoMoviesApplication extends Application {
    private BaseComponent baseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent =
                DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        baseComponent = DaggerBaseComponent.builder()
                .applicationComponent(applicationComponent)
                .networkModule(new NetworkModule(BuildConfig.api_base_url))
                .build();
    }

    public BaseComponent getBaseComponent() {
        return baseComponent;
    }
}
