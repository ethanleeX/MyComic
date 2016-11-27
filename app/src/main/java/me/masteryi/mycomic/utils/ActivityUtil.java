package me.masteryi.mycomic.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author master.yi
 * @date 2016/11/21
 * @blog masteryi.me
 */

public class ActivityUtil {
    public static void showToast(Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
