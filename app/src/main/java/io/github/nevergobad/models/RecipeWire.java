package io.github.nevergobad.models;

import android.support.annotation.Nullable;

/**
 * Created by aoriani on 5/1/16.
 */
public final class RecipeWire {
    public String name;
    public float rating;
    public String wouldPrepareAgain;
    public @Nullable String description;
    public @Nullable String image;
    public @Nullable String servings;
    public @Nullable String activeTime;
    public @Nullable String totalTime;
    public @Nullable String[] chefNotes;
    public IngredientGroup[] ingredientGroups;
    public PreparationStepGroup[] preparationStepGroups;

    public final static class IngredientGroup {
        public @Nullable String groupName;
        public String[] ingredients;
    }

    public final static class PreparationStepGroup {
        public @Nullable String groupName;
        public String[] steps;
    }

}
