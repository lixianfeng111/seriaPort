package com.licheedev.serialtool.activity.manage;

import android.view.View;
import android.widget.EditText;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备注册
 */
public class DeviceRegisterActivity extends BaseActivity {

    @BindView(R.id.editCode)
    EditText editCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_register;
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

    @OnClick({R.id.btnBack,  R.id.btLogout})
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
