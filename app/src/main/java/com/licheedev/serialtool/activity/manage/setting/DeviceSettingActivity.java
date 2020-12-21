package com.licheedev.serialtool.activity.manage.setting;

import android.content.Intent;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;

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
                //进入系统时间页面
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

    @OnClick({R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btLogout:
                break;

        }
    }

}
