package com.codepath.nevergobad.services;

import android.support.annotation.NonNull;

import com.codepath.nevergobad.models.DietaryRestriction;
import com.codepath.nevergobad.models.Recipe;
import com.codepath.nevergobad.models.RecipeSearchResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by andre on 10/06/16.
 */

public class RecipeService {
    private RecipeEndpoints mRecipeEndpoints;

    @Inject
    public RecipeService(RecipeEndpoints recipeEndpoints) {
        mRecipeEndpoints = recipeEndpoints;
    }

    public Observable<Recipe> retrieveRecipe(@NonNull String recipeId) {
        return mRecipeEndpoints.retrieveRecipe(recipeId);
    }

    public Observable<RecipeSearchResult> searchRecipes(@NonNull String searchTerms,
                                                        int page,
                                                        int pageSize,
                                                        List<DietaryRestriction> dietaryRestrictionList) {

        final int offset = (page - 1) * pageSize + 1;
        List<String> dietaryRestrictions = null;
        if (dietaryRestrictionList != null && !dietaryRestrictionList.isEmpty()) {
            dietaryRestrictions = new ArrayList<>(dietaryRestrictionList.size());
            for (DietaryRestriction dietaryRestriction: dietaryRestrictionList) {
                dietaryRestrictions.add(dietaryRestriction.value());
            }
        }

        return mRecipeEndpoints.searchRecipes(searchTerms, page, pageSize, offset, dietaryRestrictions);
    }
}
