package io.github.nevergobad.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by aoriani on 7/2/16.
 */

public final class GlideDataBidingAdapter {

    private static final String IMAGE_URL_ATTR = "imageUrl";
    private static final String PLACEHOLDER_ATTR = "placeholder";
    private static final String ERROR_ATTR = "error";

    private GlideDataBidingAdapter() {}

    @BindingAdapter({IMAGE_URL_ATTR})
    public static void glideSetterImageUrl(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    @BindingAdapter({IMAGE_URL_ATTR, PLACEHOLDER_ATTR})
    public static void glideSetterImageUrlPlaceholder(ImageView imageView, String imageUrl,
                                                      Drawable placeholder) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(placeholder)
                .into(imageView);
    }

    @BindingAdapter({IMAGE_URL_ATTR, ERROR_ATTR})
    public static void glideSetterImageUrlError(ImageView imageView, String imageUrl,
                                                Drawable error) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .error(error)
                .into(imageView);
    }

    @BindingAdapter({IMAGE_URL_ATTR, PLACEHOLDER_ATTR, ERROR_ATTR})
    public static void glideSetterImageUrlError(ImageView imageView, String imageUrl,
                                                Drawable placeholder, Drawable error) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(placeholder)
                .error(error)
                .crossFade()
                .into(imageView);
    }

}
