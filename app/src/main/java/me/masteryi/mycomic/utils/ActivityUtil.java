package me.masteryi.mycomic.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * @author master.yi
 * @date 2016/11/21
 * @blog masteryi.me
 */

public class ActivityUtil {
    public static final int NETWORK_OFFLINE = 0;
    public static final int NETWORK_MOBILE = 1;
    public static final int NETWORK_WIFI = 2;
    public static final int NETWORK_UNKNOWN = 3;

    public static void showToast (Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static int dp2px (Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }

    public static int px2dp (Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }

    /**
     * 判断是否离线
     *
     * @param context context
     * @return
     */
    public static boolean isNetWorkConnected (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
            Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable();
    }

    /**
     * 判断是否wifi状态
     *
     * @param context context
     * @return
     */
    public static boolean isWifiNetwork (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
            Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null &&
               networkInfo.getType() == ConnectivityManager.TYPE_WIFI &&
               networkInfo.isAvailable();
    }

    /**
     * 判断是否为移动网络
     *
     * @param context context
     * @return
     */
    public static boolean isMobileNetwork (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
            Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null &&
               networkInfo.getType() == ConnectivityManager.TYPE_MOBILE &&
               networkInfo.isAvailable();
    }

    /**
     * 获得网络状态
     *
     * @param context context
     * @return
     */
    public static int getNetWorkState (Context context) {
        //离线
        if(!isNetWorkConnected(context)) {
            return NETWORK_OFFLINE;
        }

        if(isWifiNetwork(context)) {
            return NETWORK_WIFI;
        }

        if(isMobileNetwork(context)) {
            return NETWORK_MOBILE;
        }

        return NETWORK_UNKNOWN;
    }
}
