package io.github.nevergobad.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;

import io.github.nevergobad.R;

/**
 * Created by aoriani on 7/17/16.
 */

public final class Recipe {
    public final @NonNull String title;
    public final float rating;
    public final String wouldMakeAgain;
    public final @Nullable String infoText;
    public final @Nullable String imageUrl;
    public final @Nullable String servings;
    public final @Nullable String activeTime;
    public final @Nullable String totalTime;


    private Recipe(@NonNull RecipeWire wire) {
        title = wire.name;
        rating = wire.rating;
        wouldMakeAgain = wire.wouldPrepareAgain;
        infoText = wire.description;
        servings = wire.servings;
        activeTime = wire.activeTime;
        totalTime = wire.totalTime;
        imageUrl = wire.image;
    }


    public CharSequence getServingInfo(@NonNull Context context) {
        final String HTML_LINE_BREAK = context.getString(R.string.html_line_break);
        StringBuilder builder = new StringBuilder();

        if (!TextUtils.isEmpty(servings)) {
            builder.append(context.getString(R.string.recipe_details_servings, servings));
        }

        if (!TextUtils.isEmpty(activeTime)) {
            if (builder.length() > 0) {
                builder.append(HTML_LINE_BREAK);
            }
            builder.append(context.getString(R.string.recipe_details_active_time, activeTime));
        }

        if (!TextUtils.isEmpty(totalTime)) {
            if (builder.length() > 0) {
                builder.append(HTML_LINE_BREAK);
            }
            builder.append(context.getString(R.string.recipe_details_total_time, totalTime));
        }

        //noinspection deprecation
        return (builder.length() > 0) ? Html.fromHtml(builder.toString()) : null;
    }

    public static Recipe from(RecipeWire wire) {
        return new Recipe(wire);
    }
}
