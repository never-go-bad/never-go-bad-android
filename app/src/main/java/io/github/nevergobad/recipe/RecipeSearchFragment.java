package io.github.nevergobad.recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import io.github.nevergobad.databinding.RecipeSearchFragmentBinding;
import io.github.nevergobad.dependencyinjection.ComponentManager;
import io.github.nevergobad.dependencyinjection.RecipeServiceModule;
import io.github.nevergobad.models.RecipeSearchResult;
import io.github.nevergobad.services.RecipeService;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by aoriani on 6/26/16.
 */

public class RecipeSearchFragment extends Fragment {

    @Inject
    RecipeService mRecipeService;

    RecipeSearchFragmentBinding mBinding;

    private RecipeSearchResultAdapter mAdapter;

    public RecipeSearchFragment() {
        super();
        ComponentManager.get().getNetworkComponent().plus(new RecipeServiceModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = RecipeSearchFragmentBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mBinding.list.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new RecipeSearchResultAdapter();
        mBinding.list.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mRecipeService.searchRecipes("chicken", 1, 10, null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<RecipeSearchResult>>() {
                    @Override
                    public void onSuccess(List<RecipeSearchResult> value) {
                        mAdapter.setItems(value);
                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                });
    }
}
