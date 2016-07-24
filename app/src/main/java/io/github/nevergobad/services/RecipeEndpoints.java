package io.github.nevergobad.services;

import android.support.annotation.NonNull;

import io.github.nevergobad.models.RecipeWire;
import io.github.nevergobad.models.RecipeSearchResultsWire;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by aoriani on 5/1/16.
 */
public interface RecipeEndpoints {

    @GET("v2/recipes?sort=1") //sort=1 sort by rating
    Single<RecipeSearchResultsWire> searchRecipes(@NonNull @Query("search") String searchTerms,
                                                  @Query("pageNumber") Integer pageNumber,
                                                  @Query("pageSize") Integer pageSize,
                                                  @Query("resultOffset") Integer pageOffset,
                                                  @Query("att") List<String> dietaryRestrictions);

    @GET("recipe/{id}") //id works a path
    Single<RecipeWire> retrieveRecipe(@NonNull @Path(value = "id", encoded = true) String id); //encoded = true should preserve '/' from id.
}
