package com.licheedev.serialtool.activity.manage.manage;

import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LogOutUtil;

import butterknife.OnClick;

/**
 * 数据上传
 */
public class DataUploadActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_upload;
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

    @OnClick({R.id.tv_depositdata, R.id.tv_bill_data, R.id.btnBack, R.id.btnUpload, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_depositdata:
                break;
            case R.id.tv_bill_data:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnUpload:
                break;
            case R.id.btLogout:
                LogOutUtil.LogOut(this, LoginActivity.class);
                finish();
                break;
        }
    }

}
