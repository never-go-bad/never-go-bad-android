package io.github.nevergobad.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by aoriani on 7/17/16.
 */

public final class Recipe {
    public final @NonNull String title;
    public final float rating;
    public final String wouldMakeAgain;
    public final @Nullable String infoText;
    public final @Nullable String imageUrl;
    public final CharSequence servingInfo;



    private Recipe(@NonNull RecipeWire wire) {
        title = wire.name;
        rating = wire.rating;
        wouldMakeAgain = wire.wouldPrepareAgain;
        infoText = wire.description;
        servingInfo = assembleServingInfo(wire);
        imageUrl = wire.image;
    }


    private CharSequence assembleServingInfo(@NonNull RecipeWire recipeWire) {
        return null;
    }

    public static Recipe from(RecipeWire wire) {
        return new Recipe(wire);
    }
}
