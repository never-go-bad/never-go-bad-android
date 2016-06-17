package io.github.nevergobad.services;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import io.github.nevergobad.models.DietaryRestriction;
import io.github.nevergobad.models.Recipe;
import io.github.nevergobad.models.RecipeSearchResult;

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

        final int offset = calculatePageOffset(page, pageSize);
        List<String> dietaryRestrictions = convertDietaryRestrictionsToQuery(dietaryRestrictionList);

        return mRecipeEndpoints.searchRecipes(searchTerms, page, pageSize, offset, dietaryRestrictions);
    }

    @VisibleForTesting
    @Nullable
    List<String> convertDietaryRestrictionsToQuery(List<DietaryRestriction> dietaryRestrictionList) {
        List<String> dietaryRestrictions = null;
        if (dietaryRestrictionList != null && !dietaryRestrictionList.isEmpty()) {
            dietaryRestrictions = new ArrayList<>(dietaryRestrictionList.size());
            for (DietaryRestriction dietaryRestriction: dietaryRestrictionList) {
                dietaryRestrictions.add(dietaryRestriction.value());
            }
        }
        return dietaryRestrictions;
    }

    @VisibleForTesting
    int calculatePageOffset(int page, int pageSize) {
        return (page - 1) * pageSize + 1;
    }
}
