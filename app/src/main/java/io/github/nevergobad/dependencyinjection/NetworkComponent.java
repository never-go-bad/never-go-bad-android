package io.github.nevergobad.dependencyinjection;

import javax.inject.Singleton;

import dagger.Component;
import io.github.nevergobad.services.RecipeEndpoints;

/**
 * Created by andre on 05/06/16.
 */

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, StaticConfigModule.class})
public interface NetworkComponent {
    RecipeServiceComponent plus(RecipeServiceModule recipeServiceModule);
}
