package com.licheedev.serialtool.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.message.LogManager;
import com.licheedev.serialtool.util.SystemErrorsUtil;
import com.licheedev.serialtool.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;

public class CheckingActivity extends BaseActivity {

    private ArrayList<String> errorList;
    private ArrayList<String> errorList2;
    @BindView(R.id.text_check)
    TextView text_check;
    private int isDialog=0;
    int[] commandWorkMode = new int[]{0xA1, 0xA2, 0xA3, 0xA4,/*STX 4byte*/
            0x12, 0x00,/*size 2byte*/
            0x21,/*CMD 1byte*/
            0x02, 0x00, 0x01, 0x08, 0x00, 0x04, 0x02, 0x06, 0x01, 0x00, 0x01, 0x01, 0x01, 0x05,/*DATA1 14byte*/
            0xBB, 0xBB, 0x39}; //进入工作模式
    private SystemErrorsUtil systemErrorsUtil=null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checking;
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCheckEvent(LogManager.ReceiveCheckData data) {
        errorList = data.errorList;
//        ToastUtil.show(this,errorList.size()+"");
        if (isDialog<=1){
            isDialog++;
//            isDialog++;
            if(errorList!=null){
                errorList2 = data.errorList;
                for (int i = 0; i <errorList2.size() ; i++) {
                    String s = errorList2.get(i);
                    checkDialog(i,s);
                    text_check.setText(s);
                }
            }else {
                intent();
            }

        }
    }

    private void intent(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    startActivity(new Intent(CheckingActivity.this,LoginActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void initView() {
        super.initView();
        if (systemErrorsUtil==null){
            systemErrorsUtil = new SystemErrorsUtil(this);
        }
        SerialPortManager.instance().initDevice();
        SerialPortManager.instance().sendStatusCommand();
    }

    @Override
    public void initListener() {

//        SerialPortManager.instance().sendCommand(SerialPortManager.byteArrayToHexString(commandWorkMode));
//        SerialPortManager.instance().sendStatusCommand();
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

    private void checkDialog(final int i, String s){
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.checking_dialog);
        Button btRetry = window.findViewById(R.id.btRetry);
        Button btSkip = window.findViewById(R.id.btSkip);
        TextView error_season = window.findViewById(R.id.error_season);
        error_season.setText(s);
        alertDialog.getWindow().setLayout(400, 210);

        btRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                SystemErrorsUtil.clearErrorList();
                isDialog=0;
                SerialPortManager.instance().sendStatusCommand();
            }
        });
        btSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                if (i ==errorList2.size()-1){
                    intent();
                }
                ToastUtil.show(CheckingActivity.this,i+"");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SerialPortManager.instance().close();
    }
}
