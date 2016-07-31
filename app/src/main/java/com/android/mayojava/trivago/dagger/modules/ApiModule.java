package com.android.mayojava.trivago.dagger.modules;

import com.android.mayojava.trivago.dagger.scope.CustomScope;
import com.android.mayojava.trivago.repository.api.MoviesService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by mayowa.adegeye on 30/07/2016.
 */
@Module
public class ApiModule {
    @Provides
    @CustomScope
    MoviesService providesMovieService(Retrofit retrofit) {
        return new MoviesService(retrofit);
    }
}
