package com.licheedev.serialtool.activity.manage.setting;

import android.content.Intent;
import android.content.res.Configuration;

import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LanguageUtils;
import com.licheedev.serialtool.util.LogOutUtil;
import com.licheedev.serialtool.util.SpzUtils;


import java.lang.reflect.Method;
import java.util.Locale;

import butterknife.OnClick;

/**
 * 机器设定
 */
public class DeviceSettingActivity extends BaseActivity {


    private TextView basic_information;
    private TextView system_time;
    private String language;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_setting;
    }

    @Override
    public void initListener() {

        basic_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeviceSettingActivity.this,BasicInformationActivity.class));
            }
        });
        system_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
            }
        });
    }

    private void systemLanguage() {
        try {
            Class<?> clzIActMag = Class.forName("android.app.IActivityManager");
            Class<?> clzActMagNative = Class.forName("android.app.ActivityManagerNative");
            Method mtdActMagNative$getDefault = clzActMagNative.getDeclaredMethod("getDefault");
            Object objIActMag = mtdActMagNative$getDefault.invoke(clzActMagNative);
            Method mtdIActMag$getConfiguration = clzIActMag.getDeclaredMethod("getConfiguration");
            Configuration config = (Configuration) mtdIActMag$getConfiguration.invoke(objIActMag);
                config.locale = Locale.ENGLISH;
            if (true) {
                Class<?>[] clzParams = {Configuration.class};
                Method mtdIActMag$updateConfiguration = clzIActMag.getDeclaredMethod("updateConfiguration", clzParams);
                mtdIActMag$updateConfiguration.invoke(objIActMag, config);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        basic_information = findViewById(R.id.basic_information);
        system_time = findViewById(R.id.system_time);
    }

    @OnClick({R.id.btnBack, R.id.btLogout, R.id.tvDeviceManage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btLogout:
                LogOutUtil.LogOut(this, LoginActivity.class);
                finish();
                break;
            case  R.id.tvDeviceManage:
                boolean language = SpzUtils.getBoolean("language", false);
                if (language){
                    language=false;
                }else {
                    language=true;
                }
                SpzUtils.putBoolean("language",language);
                LanguageUtils.attachBaseContext(this,language);
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                break;
        }
    }
}
