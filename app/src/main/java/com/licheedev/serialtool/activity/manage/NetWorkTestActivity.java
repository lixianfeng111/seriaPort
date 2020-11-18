package com.licheedev.serialtool.activity.manage;

import android.view.View;
import android.widget.EditText;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 网络测试
 */
public class NetWorkTestActivity extends BaseActivity {

    @BindView(R.id.editIp)
    EditText editIp;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_network_test;
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

    @OnClick({R.id.btnBack, R.id.btnUpload, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnUpload:
                break;
            case R.id.btLogout:
                break;

        }
    }

}
