package io.github.nevergobad.recipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.github.nevergobad.R;
import io.github.nevergobad.models.Recipe;

/**
 * Created by aoriani on 8/7/16.
 */

/*package*/ class ProcedureIngredientPageAdapter extends PagerAdapter {

    private Context mContext;
    private Recipe mRecipe;

    private static final @StringRes int[] pageTitles = {
            R.string.recipe_details_tab_ingredients,
            R.string.recipe_details_tab_procedures
    };

    public ProcedureIngredientPageAdapter(@NonNull Context context, @NonNull Recipe recipe) {
        super();
        mContext = context;
        mRecipe = recipe;
    }

    @Override
    public int getCount() {
        return pageTitles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View page = inflater.inflate(R.layout.recipe_details_viewpager_page, container, false);
        TextView content = (TextView) page.findViewById(R.id.content);
        content.setText(position == 0 ? mRecipe.formattedIngredients : mRecipe.formattedSteps);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        container.addView(page, layoutParams);

        return page;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getString(pageTitles[position]);
    }
}
