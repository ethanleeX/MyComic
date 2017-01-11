package me.masteryi.mycomic;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.stetho.Stetho;

/**
 * @author master.yi
 * @date 2016/12/6
 * @blog masteryi.me
 */

public class MyApplication extends Application {
    @Override
    public void onCreate () {
        super.onCreate();
        Fresco.initialize(this);
        Stetho.initializeWithDefaults(this);
    }
}
