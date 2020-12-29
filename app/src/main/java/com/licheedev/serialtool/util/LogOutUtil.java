package com.licheedev.serialtool.util;

import android.app.Activity;
import android.content.Intent;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/***
 * 注销（返回登陆页）
 */
public class LogOutUtil {
    public static void LogOut(Activity activity,Class<?> tClass){
        Intent intent_login = new Intent();
        intent_login.setClass(activity,tClass);
        intent_login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //关键的一句，将新的activity置为栈顶，清空其他activity
        startActivity(intent_login);
    }

}
