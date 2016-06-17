package io.github.nevergobad.models;

import android.support.annotation.Nullable;

/**
 * Created by aoriani on 5/1/16.
 */
public class RecipeSearchResult {

    public RecipeSummary[] recipes;

    public static class RecipeSummary {
        public String name;
        public @Nullable String image;
        public String id;
        public @Nullable Double rating;
    }
}
