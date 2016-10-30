package io.github.nevergobad.services;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import io.github.nevergobad.models.DietaryRestriction;
import io.github.nevergobad.models.Recipe;
import io.github.nevergobad.models.RecipeWire;
import io.github.nevergobad.models.RecipeSearchResult;
import io.github.nevergobad.models.RecipeSearchResultsWire;
import io.reactivex.Single;
import io.reactivex.functions.Function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by andre on 10/06/16.
 */

public class RecipeService {
    private RecipeEndpoints mRecipeEndpoints;
    private static RecipeSearchResultMapper sSearchResultMapper = new RecipeSearchResultMapper();
    private static RecipeMapper sRecipeMapper = new RecipeMapper();

    @Inject
    public RecipeService(RecipeEndpoints recipeEndpoints) {
        mRecipeEndpoints = recipeEndpoints;
    }

    public Single<Recipe> retrieveRecipe(@NonNull String recipeId) {
        return mRecipeEndpoints.retrieveRecipe(recipeId).map(sRecipeMapper);
    }

    public Single<List<RecipeSearchResult>> searchRecipes(@NonNull String searchTerms,
                                                          int page,
                                                          int pageSize,
                                                          List<DietaryRestriction> dietaryRestrictionList) {

        final int offset = calculatePageOffset(page, pageSize);
        List<String> dietaryRestrictions = convertDietaryRestrictionsToQuery(dietaryRestrictionList);

        return mRecipeEndpoints.searchRecipes(searchTerms, page, pageSize, offset,
                dietaryRestrictions).map(sSearchResultMapper);
    }

    @VisibleForTesting
    @Nullable
    List<String> convertDietaryRestrictionsToQuery(@Nullable List<DietaryRestriction> dietaryRestrictionList) {
        List<String> dietaryRestrictions = null;
        if (dietaryRestrictionList != null && !dietaryRestrictionList.isEmpty()) {
            dietaryRestrictions = new ArrayList<>(dietaryRestrictionList.size());
            for (DietaryRestriction dietaryRestriction : dietaryRestrictionList) {
                dietaryRestrictions.add(dietaryRestriction.value());
            }
        }
        return dietaryRestrictions;
    }

    @VisibleForTesting
    int calculatePageOffset(@IntRange(from = 1) int page, @IntRange(from = 1) int pageSize) {

        if (page < 1) {
            throw new IllegalArgumentException("page shall be >= 1");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("pageSize shall be >= 1");
        }

        return (page - 1) * pageSize + 1;
    }


    @VisibleForTesting
    static class RecipeSearchResultMapper implements Function<RecipeSearchResultsWire, List<RecipeSearchResult>> {

        @NonNull
        @Override
        public List<RecipeSearchResult> apply(@Nullable RecipeSearchResultsWire recipeSearchResultsWire) {
            if (recipeSearchResultsWire != null && recipeSearchResultsWire.recipes != null) {
                List<RecipeSearchResult> list = new ArrayList<>(recipeSearchResultsWire.recipes.length);
                for (RecipeSearchResultsWire.RecipeSummary summary : recipeSearchResultsWire.recipes) {
                    list.add(RecipeSearchResult.from(summary));
                }
                return list;
            } else {
                return Collections.emptyList();
            }
        }
    }

    @VisibleForTesting
    static class RecipeMapper implements Function<RecipeWire, Recipe> {

        @Override
        public Recipe apply(@Nullable RecipeWire wire) {
            if (wire != null) {
                return Recipe.from(wire);
            } else {
                return null;
            }
        }
    }


}
