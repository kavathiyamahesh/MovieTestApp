package com.android.mayojava.trivago.dagger.components;

import com.android.mayojava.trivago.dagger.modules.ApiModule;
import com.android.mayojava.trivago.dagger.scope.CustomScope;
import com.android.mayojava.trivago.repository.api.MoviesService;

import dagger.Component;

/**
 * Created by mayowa.adegeye on 30/07/2016.
 */
@Component(modules = {ApiModule.class}, dependencies = {ApplicationComponent.class})
@CustomScope
public interface ApiComponent extends ApplicationComponent {
    MoviesService getMovieService();
}
