package com.licheedev.serialtool.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import com.licheedev.serialtool.App;
import com.licheedev.serialtool.activity.MainActivity;

import java.util.List;
import java.util.Locale;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class LanguageUtils {


    public static Context attachBaseContext(Context context, boolean language) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //7.0及以后
            return updateResources(context,language);

        } else {
            //7.0之前
            Configuration configuration = context.getResources().getConfiguration();
            //判断是不是中文
            if (!language){
                configuration.locale =  Locale.ENGLISH;
            }else {
                configuration.locale =  Locale.CHINA;
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            context.getResources().updateConfiguration(configuration, displayMetrics);
            return context;
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context,boolean language) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        //判断是不是中文
        if (!language){
            configuration.locale =  Locale.ENGLISH;
        }else {
            configuration.locale =  Locale.CHINA;
        }
        return context.createConfigurationContext(configuration);
    }

    public static String language(Context context){
       String country = context.getResources().getConfiguration().locale.getCountry();
        return country;
    }

    public static void reStart(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
