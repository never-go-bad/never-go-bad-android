package io.github.nevergobad.recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import io.github.nevergobad.R;
import io.github.nevergobad.dependencyinjection.ComponentManager;
import io.github.nevergobad.dependencyinjection.RecipeServiceModule;
import io.github.nevergobad.services.RecipeService;

/**
 * Created by aoriani on 6/26/16.
 */

public class RecipeSearchFragment extends Fragment {

    @Inject
    RecipeService mRecipeService;

    public RecipeSearchFragment() {
        super();
        ComponentManager.get().getNetworkComponent().plus(new RecipeServiceModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_search_fragment, container, false);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mRecipeService.searchRecipes("banana", 1, 10, null);
    }
}
