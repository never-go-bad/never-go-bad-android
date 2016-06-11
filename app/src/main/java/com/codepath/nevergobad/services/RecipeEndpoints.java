package com.codepath.nevergobad.services;

import android.support.annotation.NonNull;

import com.codepath.nevergobad.models.Recipe;
import com.codepath.nevergobad.models.RecipeSearchResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by aoriani on 5/1/16.
 */
public interface RecipeEndpoints {

    @GET("recipes?sort=1")//sort=1 sort by rating
    Observable<RecipeSearchResult> searchRecipes(@NonNull @Query("search") String searchTerms,
                                                 @Query("pageNumber") Integer pageNumber,
                                                 @Query("pageSize") Integer pageSize,
                                                 @Query("resultOffset") Integer pageOffset,
                                                 @Query("att")List<String> dietaryRestrictions);

    //encoded = true should preserve '/' from id.
    @GET("recipe/{id}")
    //id works a path
    Observable<Recipe> retrieveRecipe(@NonNull @Path(value = "id", encoded = true) String id);
}
