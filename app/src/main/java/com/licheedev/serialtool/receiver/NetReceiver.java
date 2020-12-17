package com.licheedev.serialtool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.licheedev.serialtool.activity.LoginActivity;

/**
 * 用于监听开机自启
 */
public class NetReceiver extends BroadcastReceiver {

    private final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_BOOT)){
            Intent bootIntent = new Intent(context, LoginActivity.class);
            // 这里必须为FLAG_ACTIVITY_NEW_TASK
            bootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(bootIntent);
        }
    }

}
