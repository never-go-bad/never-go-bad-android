package io.github.nevergobad.recipe;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.nevergobad.models.RecipeSearchResult;

/**
 * Created by aoriani on 7/2/16.
 */

public class RecipeSearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 1;

    private List<RecipeSearchResult> mSearchResults = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                return RecipeItemViewHolder.create(parent);
            default:
                return null;
        }
    }

    public void setItems(Collection<RecipeSearchResult> items) {
        mSearchResults.clear();
        mSearchResults.addAll(items);
        notifyDataSetChanged();
    }

    public void addItems(Collection<RecipeSearchResult> items) {
        final int nextPosition  = mSearchResults.size();
        mSearchResults.addAll(items);
        notifyItemRangeInserted(nextPosition, items.size());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case VIEW_TYPE_ITEM:
                RecipeItemViewHolder itemViewHolder = (RecipeItemViewHolder) holder;
                itemViewHolder.bindData(mSearchResults.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mSearchResults.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
