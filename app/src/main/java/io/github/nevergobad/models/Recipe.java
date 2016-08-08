package io.github.nevergobad.models;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BulletSpan;
import android.text.style.StyleSpan;

import io.github.nevergobad.R;

/**
 * Created by aoriani on 7/17/16.
 */

//TODO: Break into model and view model
public final class Recipe {
    public final @NonNull String title;
    public final float rating;
    public final String wouldMakeAgain;
    public final @Nullable String infoText;
    public final @Nullable String imageUrl;
    public final @Nullable String servings;
    public final @Nullable String activeTime;
    public final @Nullable String totalTime;
    public final Spanned formattedIngredients;
    public final Spanned formattedSteps;


    private Recipe(@NonNull RecipeWire wire) {
        title = wire.name;
        rating = wire.rating;
        wouldMakeAgain = wire.wouldPrepareAgain;
        infoText = wire.description;
        servings = wire.servings;
        activeTime = wire.activeTime;
        totalTime = wire.totalTime;
        imageUrl = wire.image;
        formattedIngredients = formatIngredients(wire.ingredientGroups);
        formattedSteps = formatSteps(wire.preparationStepGroups);
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


    private Spanned formatIngredients(RecipeWire.IngredientGroup[] ingredientGroups) {
        SpannableStringBuilder builder = new SpannableStringBuilder();

        for (RecipeWire.IngredientGroup group: ingredientGroups) {
            if (!TextUtils.isEmpty(group.groupName)) {
                int start = builder.length();
                builder.append(group.groupName);
                int end = builder.length();
                builder.setSpan( new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                builder.append("\n");
            }

            for (String ingredient: group.ingredients) {
                int start = builder.length();
                builder.append(ingredient);
                int end = builder.length();
                builder.setSpan(new BulletSpan(5), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                builder.append("\n");
            }
        }

        return builder;
    }


    private Spanned formatSteps(RecipeWire.PreparationStepGroup[] stepGroups) {
        SpannableStringBuilder builder = new SpannableStringBuilder();

        for (RecipeWire.PreparationStepGroup group: stepGroups) {
            if (!TextUtils.isEmpty(group.groupName)) {
                int start = builder.length();
                builder.append(group.groupName);
                int end = builder.length();
                builder.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                builder.append("\n");
            }

            final int stepsCount = group.steps.length;
            for (int i = 1; i <= stepsCount; i++) {
                builder.append(String.valueOf(i)).append(") ").append(group.steps[i-1]).append("\n\n");
            }
        }

        return builder;
    }

    public static Recipe from(RecipeWire wire) {
        return new Recipe(wire);
    }
}
