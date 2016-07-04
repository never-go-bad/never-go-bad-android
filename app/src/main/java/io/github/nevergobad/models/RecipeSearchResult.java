package io.github.nevergobad.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by aoriani on 7/1/16.
 */

public final class RecipeSearchResult {

    public static final int MAX_RATING = 4;

    public final @NonNull String id;
    public final @NonNull String description;
    public final @Nullable String imageUrl;
    public final float rating;

    private RecipeSearchResult(RecipeSearchResultsWire.RecipeSummary data) {
        id = data.id;
        description = data.name;
        imageUrl = data.image;
        rating = data.rating == null ? 0f : data.rating.floatValue();
    }

    public static RecipeSearchResult from(RecipeSearchResultsWire.RecipeSummary recipeSummary) {
        return new RecipeSearchResult(recipeSummary);
    }
}
