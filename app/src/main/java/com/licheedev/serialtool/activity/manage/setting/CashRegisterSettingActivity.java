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
 * 收款机设定
 */
public class CashRegisterSettingActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cash_register_setting;
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

    @OnClick({R.id.tvNetwork, R.id.tvServer,  R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvNetwork:
                break;
            case R.id.tvServer: //服务器设定
                startActivity(new Intent(this,ServiceSettingActivity.class));
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
