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
 * 网络设定
 */
public class NetworkSettingActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_network_setting;
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

    @OnClick({R.id.tvCash, R.id.tvpwd, R.id.tvSerial, R.id.btnBack
            , R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvCash: //收款机设定
                startActivity(new Intent(this, CashRegisterSettingActivity.class));
                break;
            case R.id.tvpwd: //动态密码锁设置
                startActivity(new Intent(this, DynamicPwdSetActivity.class));
                break;
            case R.id.tvSerial: //串口设定
                startActivity(new Intent(this, SerialportSetActivity.class));
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
