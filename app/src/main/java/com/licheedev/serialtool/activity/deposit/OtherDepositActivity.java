package com.licheedev.serialtool.activity.deposit;

import android.content.Intent;
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
import com.licheedev.serialtool.comn.event.IsCoveringEvent;
import com.licheedev.serialtool.comn.message.LogManager;
import com.licheedev.serialtool.util.SpzUtils;
import com.sun.jna.Pointer;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
    private boolean isOpenDoor=false;
    private int n=0;
    private boolean isSaved;
    boolean isGo=false;
    int[] commandWorkMode = new int[]{0xA1, 0xA2, 0xA3, 0xA4,/*STX 4byte*/
            0x12, 0x00,/*size 2byte*/
            0x21,/*CMD 1byte*/
            0x02, 0x00, 0x01, 0x08, 0x00, 0x04, 0x02, 0x06, 0x01, 0x00, 0x01, 0x01, 0x01, 0x05,/*DATA1 14byte*/
            0xBB, 0xBB, 0x39}; //进入工作模式
    private RadioButton rd_coin;
    private RadioButton rd_check;
    private RadioButton rd_bill;
    private RadioButton rd_other;
    private int count;
    private boolean isCovered=false;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_deposit;
    }

    @Override
    protected void initView() {
        rd_coin = findViewById(R.id.rd_coin);
        rd_check = findViewById(R.id.rd_check);
        rd_bill = findViewById(R.id.rd_bill);
        rd_other = findViewById(R.id.rd_other);
        super.initView();
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
        swtichWorkMode();
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
                        SerialPortManager.instance().closeMaskDoor();//关闭罩门
                        if (isCovered){//判断有没有被遮挡
                            SerialPortManager.instance().sendSaveCommand();
                            isCovered=false;
                        }
                        isOpenDoor=false;
                        isSaved=true;
                        isGo=false;
                        editText.setText("");
                        SpzUtils.putBoolean("isPrint",true);
                    }else {
                        if (count >0){
                            SpzUtils.putInt("how_much", count);
                            TestFunction.select_deposit_Print_SampleTicket(OtherDepositActivity.this,n,h);
                            SerialPortManager.instance().openMaskDoor();//打开罩门
                            isOpenDoor=true;
                        }
                    }
                }
                break;
            case R.id.ibtn_cancel:
                SerialPortManager.instance().sendSaveAck();
                SerialPortManager.instance().sendExitWorkModeCommand();
                finish();
                break;
        }
    }

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
                    SpzUtils.putInt("currency_record",n);
                    SpzUtils.putInt("money_record",count);
                    isGo = true;
                    SerialPortManager.instance().sendSaveAck();
                    SerialPortManager.instance().sendExitWorkModeCommand();
                    finish();
                }
            }
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SerialPortManager.instance().sendSaveAck();
        SerialPortManager.instance().sendExitWorkModeCommand();
    }

    private void OpenPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom("/dev/ttyS3", 9600, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, AutoReplyPrint.CP_ComFlowControl_XonXoff, 0);
            }
        }).start();
    }

    private void swtichWorkMode() {
        SerialPortManager.instance().sendCommand(SerialPortManager.byteArrayToHexString(commandWorkMode));
    }

}
