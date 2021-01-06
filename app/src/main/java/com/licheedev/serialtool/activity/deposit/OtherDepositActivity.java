package com.licheedev.serialtool.activity.deposit;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import com.caysn.autoreplyprint.AutoReplyPrint;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.clear.TestFunction;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.SerialPortManager2;
import com.licheedev.serialtool.comn.SerialReadThread;
import com.licheedev.serialtool.comn.event.IsCoveringEvent;
import com.licheedev.serialtool.comn.message.LogManager;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.sun.jna.Pointer;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;
import static com.licheedev.serialtool.comn.message.LogManager.SAVE_SUCCESS_COMMAND;

/**
 * 存款方式选择
 */
public class OtherDepositActivity extends BaseActivity {

    @BindView(R.id.editText)
    EditText editText;
    private Pointer h=Pointer.NULL;
    //防罩门状态
    private boolean isOpenDoor;
    private int n=0;
    //有没有执行存款
    boolean isGo=false;
    private RadioButton rd_coin;
    private RadioButton rd_check;
    private RadioButton rd_bill;
    private RadioButton rd_other;
    //多少钱
    private int count;
    //遮挡状态
    private boolean isCovered;

    //循环调用机器状态查询指令
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    //调用机器状态查询指令
                    SerialPortManager.instance().sendStatusCommand();
                    //循环发送消息
                    handler.sendEmptyMessageDelayed(1,500);
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_deposit;
    }

    @Override
    protected void initView() {
        super.initView();
        rd_coin = findViewById(R.id.rd_coin);
        rd_check = findViewById(R.id.rd_check);
        rd_bill = findViewById(R.id.rd_bill);
        rd_other = findViewById(R.id.rd_other);
        //进入零钱模式
        SerialPortManager.instance().sendLooseChange();
    }

    @Override
    public void initListener() {

        rd_coin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    n=0;
                }
            }
        });
        rd_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    n=1;
                }

            }
        });
        rd_bill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    n=2;
                }

            }
        });
        rd_other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    n=3;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        OpenPort();
    }

    @OnClick({R.id.ibtn_ok, R.id.ibtn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_ok:
                Editable text = editText.getText();
                if (!TextUtils.isEmpty(text)){
                    count = Integer.parseInt(text + "");
                    if (isOpenDoor){
                        if (isCovered){//判断有没有被遮挡
                            SerialPortManager.instance().closeMaskDoor();//关闭罩门
                            SerialPortManager.instance().sendSaveCommand();//存钱
                            isCovered=false;
                            isOpenDoor=false;
                            isGo=false;
                            editText.setText("");//金额制空
                            handler.removeCallbacksAndMessages(1);//删除handler消息
                        }else {
                            ToastUtil.show(OtherDepositActivity.this,getResources().getString(R.string.put_envelope_into));
                        }
                    }else {
                        if (count >0){
                            SpzUtils.putInt("how_much", count);//保存输入金额
                            TestFunction.select_deposit_Print_SampleTicket(OtherDepositActivity.this,n,h);//打印
                            SerialPortManager.instance().openMaskDoor();//打开罩门
                            isOpenDoor=true;
                            isCovereing();
                        }
                    }
                }else if (isOpenDoor){
                    ToastUtil.show(OtherDepositActivity.this,getResources().getString(R.string.put_envelope_into));
                }
                break;
            case R.id.ibtn_cancel:
                if (isOpenDoor){
                    SerialPortManager.instance().closeMaskDoor();//关闭罩门
                    //如果打印之后不存直接返回则减去输入金额
                    TestFunction.clear_thisOther();
                }
                finish();
                break;
        }
    }

    private void isCovereing() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //SerialReadThread的遮挡状态
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onIsCoveringEvent(IsCoveringEvent isCoveringEvent){
        isCovered=isCoveringEvent.isCovering();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogManager.ReceiveData data) {
        byte[] received = data.data;
        switch (data.what) {
            case SAVE_SUCCESS_COMMAND: {
                if (!isGo){
                    //保存币种
                    SpzUtils.putInt("currency_record",n);
                    //保存金额
                    SpzUtils.putInt("money_record",count);
                    //确定存了钱
                    SpzUtils.putBoolean("isPrint",true);
                    isGo = true;
                    finish();
                }
            }
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //删除handler消息
        handler.removeCallbacksAndMessages(1);
        SerialPortManager.instance().sendSaveAck();
        //退出零钱模式
        SerialPortManager.instance().sendLooseChangeExit();
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
