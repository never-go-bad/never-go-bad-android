package io.github.nevergobad.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.nevergobad.models.DietaryRestriction;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

/**
 * Created by andre on 18/06/16.
 */

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {

    private RecipeService mRecipeService;
    private @Mock RecipeEndpoints mRecipeEndpoints;

    @Before
    public void setUp() {
        mRecipeService = new RecipeService(mRecipeEndpoints);
    }

    @After
    public void tearDown() {
        mRecipeService = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculatePageOffsetPageZero() {
        //noinspection Range
        mRecipeService.calculatePageOffset(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculatePageOffsetPageSizeZero() {
        //noinspection Range
        mRecipeService.calculatePageOffset(1, 0);
    }

    @Test
    public void calculatePageOffsetPageSizeOne() {
        assertThat(mRecipeService.calculatePageOffset(1, 1), is(equalTo(1)));
        assertThat(mRecipeService.calculatePageOffset(2, 1), is(equalTo(2)));
    }

    @Test
    public void calculatePageOffsetPageSizeTen() {
        assertThat(mRecipeService.calculatePageOffset(1, 10), is(equalTo(1)));
        assertThat(mRecipeService.calculatePageOffset(2, 10), is(equalTo(11)));
    }

    @Test
    public void convertDietaryRestrictionsToQueryNullList() {
        assertThat(mRecipeService.convertDietaryRestrictionsToQuery(null), is(nullValue()));
    }

    @Test
    public void convertDietaryRestrictionsToQueryEmptyList() {
        assertThat(mRecipeService.convertDietaryRestrictionsToQuery(Collections.<DietaryRestriction>emptyList()), is(nullValue()));
    }

    @Test
    public void convertDietaryRestrictionsToQueryWithItems() {
        List<DietaryRestriction> dietaryRestrictionList = new ArrayList<>(3);
        dietaryRestrictionList.add(DietaryRestriction.Kosher);
        dietaryRestrictionList.add(DietaryRestriction.Healthy);
        dietaryRestrictionList.add(DietaryRestriction.Vegan);

        List<String> result = mRecipeService.convertDietaryRestrictionsToQuery(dietaryRestrictionList);

        assertThat(result, hasSize(dietaryRestrictionList.size()));
        assertThat(result, contains(DietaryRestriction.Kosher.value(), DietaryRestriction.Healthy.value(), DietaryRestriction.Vegan.value()));
    }
}
