package com.licheedev.serialtool.activity.manage.setting;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 动态密码锁设置
 */
public class DynamicPwdSetActivity extends BaseActivity {

    @BindView(R.id.editIp)
    EditText editIp;
    private Button btnUpload1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dynamic_pwd;
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
        btnUpload1 = findViewById(R.id.btnUpload);
        btnUpload1.setText(getResources().getString(R.string.set));
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
