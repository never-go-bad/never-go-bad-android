package io.github.nevergobad;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.github.nevergobad.recipe.RecipeSearchFragment;
import io.github.nevergobad.settings.SettingsFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new RecipeSearchFragment();
            case 1:
                return new SettingsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
