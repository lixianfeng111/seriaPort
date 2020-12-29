package com.licheedev.serialtool.activity.manage.manage;

import android.content.Intent;
import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.activity.deposit.ClearDeviceTestActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LogOutUtil;

import butterknife.OnClick;

/**
 * 机器管理
 *
 */
public class DeviceManageActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_manage;
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

    @OnClick({R.id.tvdevicemanage, R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvdevicemanage:
                startActivity(new Intent(DeviceManageActivity.this, ClearDeviceTestActivity.class));
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
