package io.github.nevergobad.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import io.github.nevergobad.models.RecipeSearchResult;
import io.github.nevergobad.models.RecipeSearchResultsWire;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by aoriani on 7/3/16.
 */

public class RecipeSearchResultMapperTest {

    private RecipeService.RecipeSearchResultMapper mResultMapper;


    @Before
    public void setUp() {
        mResultMapper = new RecipeService.RecipeSearchResultMapper();
    }

    @After
    public void tearDown() {
        mResultMapper = null;
    }

    @Test
    public void nullEntryShallReturnEmptyList() {
        List<RecipeSearchResult> result = mResultMapper.call(null);
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(0));
    }

    @Test
    public void validEntryWithNullArrayShallReturnEmptyList() {
        RecipeSearchResultsWire nullArray = new RecipeSearchResultsWire();
        nullArray.recipes = null;
        List<RecipeSearchResult> result = mResultMapper.call(null);
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(0));
    }

    @Test
    public void validEntryWithElementShallReturnList() {
        final RecipeSearchResultsWire.RecipeSummary recipe1 = new RecipeSearchResultsWire.RecipeSummary();
        recipe1.id = "foo";
        recipe1.name = "bar";

        final RecipeSearchResultsWire.RecipeSummary recipe2 = new RecipeSearchResultsWire.RecipeSummary();
        recipe2.id = "foo";
        recipe2.name = "bar";




    }
}
