package me.masteryi.mycomic.utils;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.request.BasePostprocessor;

/**
 * @author master.yi
 * @date 2016/12/17
 * @blog masteryi.me
 */

public class BlurPostprocessor extends BasePostprocessor {
    private int mRadius = -1;

    public BlurPostprocessor () {
        mRadius = 20;
    }

    public BlurPostprocessor (int radius) {
        this.mRadius = radius;
    }

    @Override
    public String getName () {
        return super.getName();
    }

    @Override
    public void process (Bitmap bitmap) {
        FastBlur.doBlur(bitmap, mRadius, true);
    }
}
