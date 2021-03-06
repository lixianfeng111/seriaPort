package com.licheedev.serialtool.activity.manage.setting;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LogOutUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 功能设定
 */
public class FunctionSettingActivity extends BaseActivity {

    @BindView(R.id.editIp)
    EditText editIp;
    @BindView(R.id.btnUpload)
    Button btnUpload;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_function_setting;
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
        btnUpload.setText(getResources().getString(R.string.set));
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
                LogOutUtil.LogOut(this, LoginActivity.class);
                finish();
                break;

        }
    }

}
