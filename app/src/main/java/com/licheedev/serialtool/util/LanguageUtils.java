package com.licheedev.serialtool.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.List;
import java.util.Locale;

public class LanguageUtils {
    public static void shiftLanguage(String sta, Activity activity) {
        if (sta.equals("zh")) {
//            转换为英文
            Resources resources = activity.getResources();
//            获得res资源对象
            Configuration config = resources.getConfiguration();// 获得设置对象
            DisplayMetrics dm = resources.getDisplayMetrics();//
//            获得屏幕参数：主要是分辨率，像素等。
            config.locale = Locale.US;
//            英文
            resources.updateConfiguration(config, dm);
            activity.recreate();
        } else {
//            转换为中文
            Resources resources = activity.getResources();
//            获得res资源对象
            Configuration config = resources.getConfiguration();
//            获得设置对象
            DisplayMetrics dm = resources.getDisplayMetrics();
//            获得屏幕参数：主要是分辨率，像素等。
            config.locale = Locale.SIMPLIFIED_CHINESE;
//            英文
            resources.updateConfiguration(config, dm);
            activity.recreate();
        }
    }

    public static Context attachBaseContext(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //7.0及以后
            return updateResources(context);
        } else {
            //7.0之前
            Configuration configuration = context.getResources().getConfiguration();
            configuration.locale =  Locale.ENGLISH;
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            context.getResources().updateConfiguration(configuration, displayMetrics);
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = Locale.ENGLISH;
        return context.createConfigurationContext(configuration);
    }

}
