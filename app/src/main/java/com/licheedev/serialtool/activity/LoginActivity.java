package com.licheedev.serialtool.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.clear.TestFunction;
import com.licheedev.serialtool.activity.deposit.ClearDeviceTestActivity;
import com.licheedev.serialtool.activity.deposit.DepositDetailsActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.activity.deposit.SelectDepositActivitys;
import com.licheedev.serialtool.activity.manage.SetManageActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.DepositRecordUtil;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.sun.jna.Pointer;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private Pointer h=Pointer.NULL;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.editText2)
    EditText editText2;
    int ci=0;
    @OnClick({R.id.btLogin,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                checkLogin();
                break;
        }
    }

    private void checkLogin() {
        String user = editText.getText().toString();
        String passwd = editText2.getText().toString();
        if (TextUtils.isEmpty(user)&&TextUtils.isEmpty(passwd)){
            ToastUtil.show(this,"账号或密码为空");
        }else if (user.equals("111")&&passwd.equals("111")){
            SpzUtils.putString("user",user+"");
            startActivity(new Intent(this, SelectDepositActivitys.class));
            clearUserAndPassword();
        }else if (user.equals("333")&&passwd.equals("333")){
            SpzUtils.putString("user",user+"");
            startActivity(new Intent(this, ClearDeviceTestActivity.class));
            clearUserAndPassword();
        }else{
            startActivity(new Intent(this, SetManageActivity.class));
            clearUserAndPassword();
        }
    }

    private void clearUserAndPassword() {
        editText2.setText("");
        editText.setText("");
    }


    @Override
    protected void onResume() {
        super.onResume();
        boolean isPrint = SpzUtils.getBoolean("isPrint", false);
        if (isPrint){
            List<DepositDetailsActivity.DepositDetailBean> list = SpzUtils.getDataList(this, "list");
            if (list!=null){
                TestFunction.deposit_Print_SampleTicket(LoginActivity.this,list,h);
                list.clear();
                SpzUtils.setDataList(LoginActivity.this,"list", list);
                SpzUtils.putInt("num",0);
                SpzUtils.putInt("counts",0);
            }else {
                TestFunction.deposit_Print_SampleTicket(LoginActivity.this,list,h);
            }
            SpzUtils.putBoolean("isPrint", false);
        }
    }

    @SuppressLint("HardwareIds")
    @Override
    protected void initView() {
        super.initView();
        OpenPort();
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
        int ci = SpzUtils.getInt("ci", -1);
        if (ci<=0){
            DepositRecordUtil.saveDepositRecord();
        }else {
            SpzUtils.putInt("ci",1);
        }
    }
    private void OpenPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom("/dev/ttyS3", 9600, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, AutoReplyPrint.CP_ComFlowControl_XonXoff, 0);
            }
        }).start();
    }
}
