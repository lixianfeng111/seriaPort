package com.licheedev.serialtool.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.activity.deposit.SelectDepositActivitys;
import com.licheedev.serialtool.activity.manage.SetManageActivity;
import com.licheedev.serialtool.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;

    @OnClick({R.id.btLogin,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                //  startActivity(new Intent(this, SetManageActivity.class));
                if (checkLogin()) {
                    startActivity(new Intent(this, SetManageActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(this, SelectDepositActivitys.class));
                    finish();
                }
                break;
        }


    }

    private boolean checkLogin() {
        String user = editText.getText().toString();
        String passwd = editText2.getText().toString();
        return "123".equals(user) && "123".equals(passwd);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
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

}
