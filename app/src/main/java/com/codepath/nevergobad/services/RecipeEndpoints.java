package com.codepath.nevergobad.services;

import android.support.annotation.NonNull;

import com.codepath.nevergobad.models.Recipe;
import com.codepath.nevergobad.models.RecipeSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by aoriani on 5/1/16.
 */
public interface RecipeEndpoints {

    @GET("recipes?sort=1") //sort=1 sort by rating
    Call<RecipeSearchResult> searchRecipes(@NonNull @Query("search") String searchTerms,
                                           @Query("pageNumber") Integer pageNumber,
                                           @Query("pageSize") Integer pageSize,
                                           @Query("resultOffset") Integer pageOffset);

    //encoded = true should preserve '/' from id.
    @GET("recipe/{id}")
    //id works a path
    Call<Recipe> retrieveRecipe(@NonNull @Path(value = "id", encoded = true) String id);
}
