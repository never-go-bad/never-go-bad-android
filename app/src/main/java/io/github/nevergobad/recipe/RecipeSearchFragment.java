package io.github.nevergobad.recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.nevergobad.R;
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

    @BindView(R.id.list)
    RecyclerView mListView;
    private RecipeSearchResultAdapter mAdapter;

    public RecipeSearchFragment() {
        super();
        ComponentManager.get().getNetworkComponent().plus(new RecipeServiceModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_search_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RecipeSearchResultAdapter();
        mListView.setAdapter(mAdapter);
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
