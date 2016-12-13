package me.masteryi.mycomic.utils;

import android.databinding.BindingAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author master.yi
 * @date 2016/12/13
 * @blog masteryi.me
 */

public class DataBinder {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage (SimpleDraweeView view, String url) {
        view.setImageURI(url);
    }
}
