package com.licheedev.serialtool.activity.manage.update;

import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LogOutUtil;

import butterknife.OnClick;

/**
 * 安卓更新
 */
public class AndroidUpdateActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_android_update;
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

    @OnClick({R.id.tvDownload,  R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvDownload:
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
