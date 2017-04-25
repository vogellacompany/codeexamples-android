package com.vogella.android.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by vogella on 24.04.17.
 */

public class MyBindingAdapter {

//    @BindingAdapter({"bind:imageUrl", "bind:error"})
//    public static void loadImage(ImageView view, String url, Drawable error) {
////        Picasso.with(view.getContext()).load(url).error(error).into(view);
//    }
    @BindingAdapter("titleText")
    public static void setText(TextView view, String text) {
        view.setText(text);
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }


}
