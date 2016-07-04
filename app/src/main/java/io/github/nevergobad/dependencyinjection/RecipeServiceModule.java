package io.github.nevergobad.dependencyinjection;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.nevergobad.services.RecipeEndpoints;
import io.github.nevergobad.services.RecipeService;

/**
 * Created by andre on 14/06/16.
 */

@Module
public class RecipeServiceModule {
    @Inject RecipeService mRecipeService;

    @Provides
    RecipeService provideRecipeService(RecipeEndpoints recipeEndpoints) {
        return new RecipeService(recipeEndpoints);
    }
}
