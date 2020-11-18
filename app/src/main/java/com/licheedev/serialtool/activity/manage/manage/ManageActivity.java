package com.licheedev.serialtool.activity.manage.manage;

import android.content.Intent;
import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;

import butterknife.OnClick;

/**
 * 管理
 */
public class ManageActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_manage;
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

    @OnClick({R.id.tvUsermanage, R.id.tvDeviceManage, R.id.tvDataUpload, R.id.btnBack
            , R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvUsermanage:
                startActivity(new Intent(ManageActivity.this, UserManageActivity.class));
                break;
            case R.id.tvDeviceManage:
                startActivity(new Intent(ManageActivity.this, DeviceManageActivity.class));
                break;
            case R.id.tvDataUpload:
                startActivity(new Intent(ManageActivity.this, DataUploadActivity.class));
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btLogout:
                break;

        }
    }

}
