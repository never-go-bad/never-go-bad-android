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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeSearchResult that = (RecipeSearchResult) o;

        if (Float.compare(that.rating, rating) != 0) return false;
        if (!id.equals(that.id)) return false;
        if (!description.equals(that.description)) return false;
        return imageUrl != null ? imageUrl.equals(that.imageUrl) : that.imageUrl == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        return result;
    }
}
