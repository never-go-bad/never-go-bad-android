package com.codepath.nevergobad.services;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andre on 05/06/16.
 */

@Singleton
@Component(modules = {NetworkModule.class, StaticConfigModule.class})
public interface NetworkComponent {
    RecipeEndpoints recipeEndpoints();
}
