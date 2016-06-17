package io.github.nevergobad.services;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andre on 14/06/16.
 */

@Module
public class RecipeServiceModule {

    @Provides
    @Singleton
    RecipeService provideRecipeService(RecipeEndpoints recipeEndpoints) {
        return new RecipeService(recipeEndpoints);
    }
}
