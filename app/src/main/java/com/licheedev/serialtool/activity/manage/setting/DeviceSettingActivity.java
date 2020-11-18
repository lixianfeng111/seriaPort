package com.licheedev.serialtool.activity.manage.setting;

import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;

import butterknife.OnClick;

/**
 * 机器设定
 */
public class DeviceSettingActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_setting;

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

    @Override
    protected void initView() {
        super.initView();
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
