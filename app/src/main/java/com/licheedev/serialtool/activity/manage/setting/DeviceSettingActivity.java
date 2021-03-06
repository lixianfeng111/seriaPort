package com.licheedev.serialtool.activity.manage.setting;

import android.content.Intent;
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
import com.licheedev.serialtool.util.constant.Constant;

import butterknife.OnClick;

/**
 * 机器设定
 */
public class DeviceSettingActivity extends BaseActivity {


    private TextView basic_information;
    private TextView system_time;

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
            case  R.id.tvDeviceManage://语言切换
                boolean language = SpzUtils.getBoolean(Constant.LANGUAGE, false);
                if (language){
                    language=false;
                }else {
                    language=true;
                }
                SpzUtils.putBoolean(Constant.LANGUAGE,language);
                LanguageUtils.attachBaseContext(this,language);
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                break;
        }
    }
}
