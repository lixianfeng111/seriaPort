package com.licheedev.serialtool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.net.NetUtils;

/**
 * 用于实时监听app的网络状态
 */
public class NetReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent bootIntent = new Intent(context, LoginActivity.class);
        // 这里必须为FLAG_ACTIVITY_NEW_TASK
        bootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(bootIntent);
    }

}
