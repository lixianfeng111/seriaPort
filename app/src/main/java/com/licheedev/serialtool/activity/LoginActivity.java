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
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.util.GetCurrencyUtil;
import com.licheedev.serialtool.util.LanguageUtils;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.SystemErrorsUtil;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;
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
    int[] str = new int[]{};
    private String print_currency;
    private SystemErrorsUtil systemErrorsUtil=null;

    @OnClick({R.id.btLogin})
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
            ToastUtil.show(this,getResources().getString(R.string.user_or_psw));
        }else if (user.equals("111")&&passwd.equals("111")){
            SpzUtils.putString(Constant.USER,user+"");
            startActivity(new Intent(this, SelectDepositActivitys.class));
            clearUserAndPassword();
        }else if (user.equals("333")&&passwd.equals("333")){
            SpzUtils.putString(Constant.USER,user+"");
            startActivity(new Intent(this, ClearDeviceTestActivity.class));
            clearUserAndPassword();
        }else{//进入管理页面
            startActivity(new Intent(this, SetManageActivity.class));
            clearUserAndPassword();
        }
    }

    //离开登陆页之后将账号和密码制空
    private void clearUserAndPassword() {
        editText2.setText("");
        editText.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //得到币种
        print_currency = GetCurrencyUtil.getCurrency();
        //切换语言
        switch_theLanguage();
        //打印
        print();

    }


    private void print() {
        boolean isPrint = SpzUtils.getBoolean(Constant.IS_PRINT, false);
        if (isPrint){//判断是否存了钱
            List<DepositDetailsActivity.DepositDetailBean> list = SpzUtils.getDataList(this, Constant.LIST);
            if (list!=null){//判断是否存了钞票，如果存了钞票就进入if
                if (print_currency.equals(Constant.CNY)){
                    TestFunction.deposit_Print_SampleTicket(this,list,h);
                }else if (print_currency.equals(Constant.MXN)){
                    TestFunction.deposit_Print_SampleTicket_MXN(this,list,h);
                }
                list.clear();//打印之后清空list
                SpzUtils.setDataList(LoginActivity.this,Constant.LIST, list);//保存清空后的list
                //存款打印之后 金额（num）和张数(counts)置为0
                SpzUtils.putInt(Constant.NUM,0);
                SpzUtils.putInt(Constant.COUNTS,0);
            }else {//如果没有存钞票，只存了其他存款（硬币，支票等）就进入else
                if (print_currency.equals(Constant.CNY)){
                    TestFunction.deposit_Print_SampleTicket(this,list,h);
                }else if (print_currency.equals(Constant.MXN)){
                    TestFunction.deposit_Print_SampleTicket_MXN(this,list,h);
                }
            }
            //打印之后isPrint置为false
            SpzUtils.putBoolean(Constant.IS_PRINT, false);
        }
    }

    private void switch_theLanguage() {
        boolean language = SpzUtils.getBoolean(Constant.LANGUAGE,false);
        LanguageUtils.attachBaseContext(this,language);
    }

    @SuppressLint("HardwareIds")
    @Override
    protected void initView() {
        super.initView();
        //打开串口
        OpenPort();
        if (systemErrorsUtil==null){
            systemErrorsUtil = new SystemErrorsUtil(this);
        }
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
        int ci = SpzUtils.getInt(Constant.CI, 0);
        if (ci!=0) {
            SpzUtils.putInt(Constant.CI, 1);
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
        if (h != Pointer.NULL) {
            AutoReplyPrint.INSTANCE.CP_Port_Close(h);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClosePort();
    }
}
