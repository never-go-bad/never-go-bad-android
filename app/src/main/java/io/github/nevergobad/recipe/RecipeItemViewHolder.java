package io.github.nevergobad.recipe;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.github.nevergobad.DeepLinkUtils;
import io.github.nevergobad.databinding.RecipeResultItemBinding;
import io.github.nevergobad.models.Recipe;
import io.github.nevergobad.models.RecipeSearchResult;

/**
 * Created by aoriani on 7/1/16.
 */

class RecipeItemViewHolder extends RecyclerView.ViewHolder implements RecipeItemPresenter {

    private final RecipeResultItemBinding mItemBinding;

    private RecipeItemViewHolder(@NonNull RecipeResultItemBinding itemBinding) {
        super(itemBinding.getRoot());
        mItemBinding = itemBinding;
    }

    void bindData(@NonNull RecipeSearchResult recipeSearchResult) {
        mItemBinding.setRecipe(recipeSearchResult);
        mItemBinding.setPresenter(this);
    }

    public static RecipeItemViewHolder create(ViewGroup parent) {
        RecipeResultItemBinding itemBinding = RecipeResultItemBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new RecipeItemViewHolder(itemBinding);
    }

    @Override
    public void onRecipeItemClicked(Context context, RecipeSearchResult item) {
        RecipeDetailsActivity.viewRecipe(context, DeepLinkUtils.recipeFromPath(item.id), item.description);
    }
}
