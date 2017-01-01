package me.masteryi.mycomic.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import me.masteryi.mycomic.constant.MyEventAction;
import org.greenrobot.eventbus.EventBus;

public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive (Context context, Intent intent) {
        EventBus.getDefault().post(MyEventAction.NETWORK_STATE_CHANGED);
    }
}
