package com.licheedev.serialtool.activity.manage.maintain;

import android.view.View;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.clear.TestFunction;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.util.LanguageUtils;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.constant.Constant;
import com.sun.jna.Pointer;

import butterknife.OnClick;

/**
 * 系统调试
 */
public class SystemDebugActivity extends BaseActivity {

    private Pointer h=Pointer.NULL;
    private boolean language;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_debug;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initVariable() {
        SerialPortManager.instance().initDevice();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initData() {
        language = SpzUtils.getBoolean(Constant.LANGUAGE,false);
        OpenPort();
    }

    @OnClick({R.id.tv_printdebug, R.id.btnBack, R.id.btLogout, R.id.debug_hood_door_close, R.id.debug_hood_door_open})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_printdebug:
                if (language) {
                    TestFunction.select_deposit_Print_SampleTicket(this, 1, h);
                } else {
                    TestFunction.select_deposit_Print_SampleTicket_MXN(this, 1, h);
                }
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btLogout:
                break;
            case R.id.debug_hood_door_open:
                SerialPortManager.instance().openMaskDoor();
                break;
            case R.id.debug_hood_door_close:
                SerialPortManager.instance().closeMaskDoor();
                break;

        }
    }

    private void OpenPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom(Constant.PORT, Constant.BAUD_RATE, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, AutoReplyPrint.CP_ComFlowControl_XonXoff, 0);
            }
        }).start();
    }

    private void ClosePort() {
        if (h != Pointer.NULL) {
            AutoReplyPrint.INSTANCE.CP_Port_Close(h);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClosePort();
        SerialPortManager.instance().close();
    }
}
