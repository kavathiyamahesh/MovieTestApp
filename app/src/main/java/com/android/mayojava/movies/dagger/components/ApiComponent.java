package com.android.mayojava.movies.dagger.components;

import com.android.mayojava.movies.dagger.modules.ApiModule;
import com.android.mayojava.movies.dagger.scope.CustomScope;
import com.android.mayojava.movies.repository.api.MoviesService;

import dagger.Component;

/**
 * Created by mayowa.adegeye on 30/07/2016.
 */
@Component(modules = {ApiModule.class}, dependencies = {ApplicationComponent.class})
@CustomScope
public interface ApiComponent extends ApplicationComponent {
    MoviesService getMovieService();
}
