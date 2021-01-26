package com.licheedev.serialtool.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.event.CheckEvent;
import com.licheedev.serialtool.comn.event.IntentEvent;
import com.licheedev.serialtool.comn.message.LogManager;
import com.licheedev.serialtool.dialog.CurrenySelectUtil;
import com.licheedev.serialtool.util.CheckingErrorsUtil;
import com.licheedev.serialtool.util.LogPlus;
import com.licheedev.serialtool.util.SystemErrorsUtil;
import com.licheedev.serialtool.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class CheckingActivity extends BaseActivity {

    @BindView(R.id.text_check)
    TextView text_check;
    private String hexstr1=null;
    private boolean isDialog=true;
    int[] commandWorkMode = new int[]{0xA1, 0xA2, 0xA3, 0xA4,/*STX 4byte*/
            0x12, 0x00,/*size 2byte*/
            0x21,/*CMD 1byte*/
            0x02, 0x00, 0x01, 0x08, 0x00, 0x04, 0x02, 0x06, 0x01, 0x00, 0x01, 0x01, 0x01, 0x05,/*DATA1 14byte*/
            0xBB, 0xBB, 0x39}; //进入工作模式

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checking;
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckEvent(CheckEvent checkEvent) {
        hexstr1 = checkEvent.getCheck();
        if (isDialog){
            isDialog=false;
            text_check.setText(hexstr1);
            checkDialog();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void intentEvent(IntentEvent intentEvent){
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    @Override
    protected void initView() {
        super.initView();
        SerialPortManager.instance().initDevice();
        SystemErrorsUtil systemErrorsUtil = new SystemErrorsUtil(this);
        CheckingErrorsUtil checkingErrorsUtil = new CheckingErrorsUtil(this);

    }

    @Override
    public void initListener() {

        SerialPortManager.instance().sendCommand(SerialPortManager.byteArrayToHexString(commandWorkMode));
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

    public interface Callback {
        void onDialogClick(int which, Dialog dialog);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    private String content(int system_error) {
        String string = getResources().getString(system_error);
        return string;
    }

    private void checkDialog(){
        ViewGroup view = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.checking_dialog, null, false);
        Button btRetry = view.findViewById(R.id.btRetry);
        Button btSkip = view.findViewById(R.id.btSkip);
        TextView textView2 = findViewById(R.id.textView2);

        final AlertDialog alertDialog = new AlertDialog
                .Builder(this)
                .create();
        alertDialog.setView(view);
        alertDialog.show();
        alertDialog.getWindow().setLayout(400, 210);

        btRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                isDialog=true;
                SerialPortManager.instance().sendStatusCommand();
            }
        });
        btSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                isDialog=true;
            }
        });
        textView2.setText(hexstr1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
