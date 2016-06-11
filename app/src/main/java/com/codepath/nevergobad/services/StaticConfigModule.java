package com.codepath.nevergobad.services;

import com.codepath.nevergobad.BuildConfig;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andre on 11/06/16.
 */

@Module
public class StaticConfigModule {

    public static final String DEBUG = "DebugConfig";
    public static final String USER_AGENT = "UserAgent";
    public static final String RECIPE_BASE_URL = "RecipeBaseUrl";

    @Provides
    @Named(DEBUG)
    boolean provideDebugConfig() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Named(USER_AGENT)
    String provideUserAgentString() {
        return BuildConfig.APPLICATION_ID
                + "-" + BuildConfig.VERSION_NAME
                + "-" + BuildConfig.BUILD_TYPE;
    }

    @Provides
    @Named(RECIPE_BASE_URL)
    String provideRecipeBaseUrl() {
        return BuildConfig.RECIPE_BASE_URL;
    }
    
}
