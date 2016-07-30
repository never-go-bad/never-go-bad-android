package io.github.nevergobad.dependencyinjection;

import dagger.Subcomponent;
import io.github.nevergobad.recipe.RecipeDetailsActivity;
import io.github.nevergobad.recipe.RecipeSearchFragment;

/**
 * Created by andre on 14/06/16.
 */

@Subcomponent(modules = {RecipeServiceModule.class})
public interface RecipeServiceComponent {
    void inject (RecipeSearchFragment recipeSearchFragment);
    void inject (RecipeDetailsActivity recipeDetailsActivity);
}
