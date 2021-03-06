package com.licheedev.serialtool.activity.manage.setting;

import android.content.Intent;
import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LogOutUtil;

import butterknife.OnClick;

/**
 * 设定
 * 收款机
 */
public class SettingActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initListener() {

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

    @OnClick({R.id.tvDevice, R.id.tvNetwork, R.id.tvFunction, R.id.tvenvelope, R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDevice://机器设定
                startActivity(new Intent(SettingActivity.this, DeviceSettingActivity.class));
                break;
            case R.id.tvNetwork://网络设定
                startActivity(new Intent(SettingActivity.this, NetworkSettingActivity.class));
                break;
            case R.id.tvFunction://功能设定
                startActivity(new Intent(SettingActivity.this, FunctionSettingActivity.class));
                break;
            case R.id.tvenvelope://信封金额
                startActivity(new Intent(SettingActivity.this, EnvelopeSettingActivity.class));
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btLogout:
                LogOutUtil.LogOut(this, LoginActivity.class);
                finish();
                break;
        }
    }

}
