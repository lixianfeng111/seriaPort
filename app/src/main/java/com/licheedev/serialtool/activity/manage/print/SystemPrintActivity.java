package com.licheedev.serialtool.activity.manage.print;

import android.content.Intent;
import android.view.View;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LogOutUtil;
import com.licheedev.serialtool.util.constant.Constant;
import com.sun.jna.Pointer;

import butterknife.OnClick;

/**
 * 系统打印
 */
public class SystemPrintActivity extends BaseActivity {
    private Pointer h;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_print;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initVariable() {

        OpenPort();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tvcreenbill, R.id.tvclearrecord, R.id.tvdepositrecored, R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvcreenbill:
                startActivity(new Intent(this, BagActivity.class));
                break;

            case R.id.tvclearrecord:
                startActivity(new Intent(this, ClearingRecordActivity.class));
                break;

            case R.id.tvdepositrecored: //存款记录
                startActivity(new Intent(this, DealRecordActivity.class));
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
    private void OpenPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //波特率为9600
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom(Constant.PORT, Constant.BAUD_RATE, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, AutoReplyPrint.CP_ComFlowControl_XonXoff, 0);
            }
        }).start();
    }

    private void ClosePort() {
        AutoReplyPrint.INSTANCE.CP_Port_Close(h);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClosePort();
    }
}
