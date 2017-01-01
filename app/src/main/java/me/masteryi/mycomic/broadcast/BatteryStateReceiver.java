package me.masteryi.mycomic.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import me.masteryi.mycomic.constant.MyEventAction;
import org.greenrobot.eventbus.EventBus;

/**
 * @author master.yi
 * @date 2016/12/31
 * @blog masteryi.me
 */

public class BatteryStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive (Context context, Intent intent) {
        EventBus.getDefault().post(MyEventAction.BATTERY_STATE_CHANGED);
    }
}
