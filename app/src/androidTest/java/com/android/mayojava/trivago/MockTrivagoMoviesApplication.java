package com.android.mayojava.trivago;

import com.android.mayojava.trivago.dagger.components.ApplicationComponent;

/**
 * Created by mayowa.adegeye on 02/08/2016.
 */
public class MockTrivagoMoviesApplication extends TrivagoMoviesApplication {
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
