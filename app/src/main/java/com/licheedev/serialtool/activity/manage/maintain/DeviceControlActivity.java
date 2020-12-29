package com.licheedev.serialtool.activity.manage.maintain;

import android.view.View;
import android.widget.Button;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LogOutUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设备控制
 */
public class DeviceControlActivity extends BaseActivity {

    @BindView(R.id.btLogout)
    Button mandatorymodeBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_control;
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
        mandatorymodeBtn.setText(getResources().getString(R.string.mandatorymode));
    }

    @OnClick({R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
