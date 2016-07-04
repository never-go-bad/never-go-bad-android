package io.github.nevergobad.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by aoriani on 7/2/16.
 */

public class GlideDataBiding {

    @BindingAdapter({"imageUrl"})
    public static void glideSetterImageUrl(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
    }

    @BindingAdapter({"imageUrl", "placeholder"})
    public static void glideSetterImageUrlPlaceholder(ImageView imageView, String imageUrl,
                                                      Drawable placeholder) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(placeholder)
                .into(imageView);
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void glideSetterImageUrlError(ImageView imageView, String imageUrl,
                                                Drawable error) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .error(error)
                .into(imageView);
    }

    @BindingAdapter({"imageUrl", "placeholder", "error"})
    public static void glideSetterImageUrlError(ImageView imageView, String imageUrl,
                                                Drawable placeholder, Drawable error) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(placeholder)
                .error(error)
                .into(imageView);
    }

}
