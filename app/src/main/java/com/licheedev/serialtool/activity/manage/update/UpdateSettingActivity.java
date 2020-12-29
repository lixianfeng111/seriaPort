package com.licheedev.serialtool.activity.manage.update;

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
 * 更新设置
 */
public class UpdateSettingActivity extends BaseActivity {

    @BindView(R.id.btnUpload)
    Button btnUpload;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_setting;
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

    @OnClick({R.id.btnUpload, R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.btnUpload:

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
