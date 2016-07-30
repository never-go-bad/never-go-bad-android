package io.github.nevergobad;

import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by aoriani on 7/29/16.
 */

public final class DeepLinkUtils {

    public static final String NEVER_GO_BAD_SCHEMA = "nevergobad";

    public static final String RECIPE_HOST = "recipe";

    private DeepLinkUtils() {}




    public static Uri recipeFromPath(@NonNull String path) {
        return new Uri.Builder()
                .scheme(NEVER_GO_BAD_SCHEMA)
                .authority(RECIPE_HOST)
                .appendEncodedPath(path)
                .build();
    }



}
