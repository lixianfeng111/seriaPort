package com.licheedev.serialtool;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.util.PrefHelper;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class App extends Application {

    private Handler mUiHandler;
    private static App sInstance;
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mUiHandler = new Handler();
        initUtils();
        SerialPortManager.instance().initDevice();
    }

    private void initUtils() {
        PrefHelper.initDefault(this);
    }

    public static App instance() {
        return sInstance;
    }

    public static Handler getUiHandler() {
        return instance().mUiHandler;
    }
}
