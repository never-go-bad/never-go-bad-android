package io.github.nevergobad.models;

import android.support.annotation.NonNull;

/**
 * Created by aoriani on 7/17/16.
 */

public final class Recipe {
    public final @NonNull String title;
    public final double rating;



    private Recipe(@NonNull RecipeWire wire) {
        title = wire.name;
        rating = wire.rating;
    }

    public static Recipe from(RecipeWire wire) {
        return new Recipe(wire);
    }
}
