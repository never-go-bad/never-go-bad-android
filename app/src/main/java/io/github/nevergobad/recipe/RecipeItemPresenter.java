package io.github.nevergobad.recipe;

import android.content.Context;

import io.github.nevergobad.models.RecipeSearchResult;

/**
 * Created by aoriani on 7/16/16.
 */

public interface RecipeItemPresenter {
    void onRecipeItemClicked(Context context, RecipeSearchResult item);
}
