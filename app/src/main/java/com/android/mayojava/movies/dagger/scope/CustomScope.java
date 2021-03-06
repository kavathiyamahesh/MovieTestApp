package com.android.mayojava.movies.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * custom scope
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomScope {
}
