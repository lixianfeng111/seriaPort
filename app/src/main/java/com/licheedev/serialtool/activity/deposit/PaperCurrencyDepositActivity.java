package com.licheedev.serialtool.activity.deposit;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.event.IsCoveringEvent;
import com.licheedev.serialtool.comn.message.LogManager;
import com.licheedev.serialtool.dialog.CurrenySelectUtil;
import com.licheedev.serialtool.util.LogPlus;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;
import com.licheedev.serialtool.util.constant.Money;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import static com.licheedev.serialtool.comn.message.LogManager.COUNT_COMMAND;
import static com.licheedev.serialtool.comn.message.LogManager.EXIT_WORK_COMMAND;
import static com.licheedev.serialtool.comn.message.LogManager.FINISH_DEPOSIT;
import static com.licheedev.serialtool.comn.message.LogManager.SEARCH_LEAD;

/**
 * 纸币存款
 */
public class PaperCurrencyDepositActivity extends BaseActivity {

    public static final int REQUEST_CODE_DEPOSIT = 1;
    public static final int RESULT_CODE_DEPOSIT = 1111;
    public static final int CURRENCY = 11;//币种
    private boolean takeOut;

    private int refundMoney=0;
    private int n=0;
    private int m=0;
    private boolean close=false;
    int[] commandWorkMode = new int[]{0xA1, 0xA2, 0xA3, 0xA4,/*STX 4byte*/
            0x12, 0x00,/*size 2byte*/
            0x21,/*CMD 1byte*/
            0x02, 0x00, 0x01, 0x08, 0x00, 0x04, 0x02, 0x06, 0x01, 0x00, 0x01, 0x01, 0x01, 0x05,/*DATA1 14byte*/
            0xBB, 0xBB, 0x39}; //进入工作模式

    @BindView(R.id.tvCurrencyNum)
    TextView tvCurrencyNum;
    @BindView(R.id.tvMoneyNum)
    TextView tvMoneyNum;
    @BindView(R.id.tvRrfuse)
    TextView tvRrfuse;
    @BindView(R.id.llLead)
    LinearLayout mLead;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.btnCurrency)
    Button btnCurrency;
    @BindView(R.id.refund_reason)
    TextView refund_reason;
    //遮挡状态
    private boolean isCovered;
    Deposit deposit;
    boolean exit;
    Dialog continueDepositDialogdialog, exitFailDialog0, exitFailDialog1;
    private String currency;
    private int count;
    private int money;
    private ArrayList<String> errorList2;
    private List<RefundMoneyActivity.RefundMoneyBean> list=new ArrayList<>();
    private RefundMoneyActivity.RefundMoneyBean refundMoneyBean;
    private Thread thread;

    public static class Deposit {

        public Deposit(long moneyNum, long currencyNum, long refuse) {
            this.moneyNum = moneyNum;
            this.currencyNum = currencyNum;
            this.refuse = refuse;
        }

        long moneyNum;
        long currencyNum;
        long refuse;

        boolean isEmpty() {
            return moneyNum == 0 && currencyNum == 0 && refuse == 0;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        swtichWorkMode();

    }

    private void swtichWorkMode() {
        SerialPortManager.instance().sendCommand(SerialPortManager.byteArrayToHexString(commandWorkMode));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R.id.ibtn_ok, R.id.ibtn_cancel, R.id.btnCurrency, R.id.llLead,R.id.tvStatus,R.id.refund_reason})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_ok:
                if (exit){
                        SerialPortManager.instance().sendSaveCommand();
                        startActivityForResult(new Intent(this, DepositDetailsActivity.class), REQUEST_CODE_DEPOSIT);
                        exit=false;
                }
                break;
            case R.id.ibtn_cancel:
                if (deposit == null || deposit.isEmpty()) {
                    finish();
                    return;
                }
                SerialPortManager.instance().sendStatusCommand();
                    continueDepositDialogdialog = CurrenySelectUtil.showContinueDepositDialog(this, new Callback() {
                        @Override
                        public void onDialogClick(int which, Dialog dialog) {
                            if (which == 0) {
                                continueDepositDialogdialog.dismiss();
                                SerialPortManager.instance().sendExitWorkModeCommand();
                            }
                        }
                    });
                break;

            case R.id.btnCurrency: //币种选择

                CurrenySelectUtil.showCurreny(this, new Callback() {
                    @Override
                    public void onDialogClick(int position, Dialog dialog) {
                        if (position==3){
                                dialog.dismiss();
                                btnCurrency.setText(Constant.CNY);
                                currency=Constant.CNY;
                                SerialPortManager.instance().sendCNRCommand();
                                SpzUtils.putString(Constant.PRINT_CURRENCY,Constant.CNY);
                            }else if (position==4){
                                dialog.dismiss();
                                btnCurrency.setText(Constant.MXN);
                                currency=Constant.MXN;
                                SerialPortManager.instance().sendMXNCommand();
                                SpzUtils.putString(Constant.PRINT_CURRENCY,Constant.MXN);
                        }
                    }
                });
                break;
            case R.id.llLead: // 查看拒钞详情
                // Step 1： 发送查询拒钞原因指令
                SerialPortManager.instance().sendLeadCommand();
                break;
            case R.id.tvStatus:
                m++;
                int size = errorList2.size();
                if (size>1){
                    if (m<size){
                        String s = errorList2.get(m);
                        tvStatus.setText(s + " : "+getResources().getString(R.string.press_to_clear));
                    }else {
                        n=0;
                        m=0;
                        tvStatus .setVisibility(View.GONE);
                    }
                }
                SerialPortManager.instance().sendFAILURE_RESET();

                break;
            case R.id.refund_reason:
                SpzUtils.setErrorReasonList(this,Constant.LIST_ERROR,list);
                startActivity(new Intent(this,RefundMoneyActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ArrayList<String> errorList) {
        if (n==0){
            errorList2=errorList;
            String s = errorList.get(0);
            tvStatus.setText(s + " : "+getResources().getString(R.string.press_to_clear));
            tvStatus .setVisibility(View.VISIBLE);
            n=1;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogManager.ReceiveData data) {
        int CNY_1000=0,CNY_500=0,CNY_200=0,CNY_100=0,CNY_50=0,CNY_20=0,CNY_10=0,CNY_5=0,CNY_1=0;
        byte[] received = data.data;
        switch (data.what) {
            case COUNT_COMMAND: {
                if (currency.equals(Constant.CNY)){
                    CNY_100 = received[9] + (received[10] << 8);
                    CNY_50 = received[11] + (received[12] << 8);
                    CNY_20 = received[13] + (received[14] << 8);
                    CNY_10 = received[15] + (received[16] << 8);
                    CNY_5 = received[17] + (received[18] << 8);
                    CNY_1 = received[19] + (received[20] << 8);
                    final String amount =" 收到  100x" + CNY_100 + " 50x" +
                            CNY_50 + " 20x" + CNY_20 + " 10x" + CNY_10 + " 5x" + CNY_5 + " 1x" + CNY_1;
                    LogPlus.e("read_thread", amount);

                }else if (currency.equals(Constant.MXN)){
                    CNY_1000 = received[9] + (received[10] << 8);
                    CNY_500 = received[11] + (received[12] << 8);
                    CNY_200 = received[13] + (received[14] << 8);
                    CNY_100 = received[15] + (received[16] << 8);
                    CNY_50 = received[17] + (received[18] << 8);
                    CNY_20 = received[19] + (received[20] << 8);
                    final String amount =" 收到  1000x" + CNY_1000 + " 500x" +
                            CNY_500 + " 200x" + CNY_200 + " 100x" + CNY_100 + " 50x" + CNY_50 + " 20x" + CNY_20;
                    LogPlus.e("read_thread", amount);
                }
                count = CNY_1000 +
                        CNY_500 +
                        CNY_200 +
                        CNY_100 +
                        CNY_50 +
                        CNY_20 +
                        CNY_10 +
                        CNY_5 +
                        CNY_1;
                tvCurrencyNum.setText("" + count);
                money =CNY_1000 * Money.Denomination_1000_CNY +
                        CNY_500 * Money.Denomination_500_CNY +
                        CNY_200 * Money.Denomination_200_CNY +
                        CNY_100 * Money.Denomination_100_CNY +
                        CNY_50 * Money.Denomination_50_CNY +
                        CNY_20 * Money.Denomination_20_CNY +
                        CNY_10 * Money.Denomination_10_CNY +
                        CNY_5 * Money.Denomination_5_CNY +
                        CNY_1 * Money.Denomination_1_CNY;
                tvMoneyNum.setText("" + money);
                int reject = received[49];

                tvRrfuse.setText("" + reject);
                if (reject>0){
//                    refundMoney=0;
                    SerialPortManager.instance().sendRefundMoney();
                    refund_reason.setVisibility(View.VISIBLE);
                }else {
                    refund_reason.setVisibility(View.GONE);
                    list.clear();
                }
                if (count>0){
                    exit=true;
                    isCovered=false;
                }
                else if (takeOut){
                    close=true;
                }

                deposit = new Deposit(money, count, reject);
            }
            break;
            case EXIT_WORK_COMMAND: {
                /**
                 * 06H 退出成功
                 * 15H 未处在工作模式，不能执行退出操作
                 * 16H 收钞口有纸币，不能执行退出操作
                 * 17H 退钞口有纸币，不能执行退出操作
                 * 18H 置钞口有纸币，不能执行退出操作
                 */
                if (((char) (received[7] & 0xff) == 0x06)) {
                    LogPlus.e("read_thread", "0x06 退出成功");
                    finish();
                } else if (((char) (received[7] & 0xff) == 0x15)) {

                    LogPlus.e("read_thread", "0x15 不能执行退出操作");

                } else if (((char) (received[7] & 0xff) == 0x16)) {

                    SerialPortManager.instance().openMaskDoor();
                    if (exitFailDialog0 != null && exitFailDialog0.isShowing()) {
                        exitFailDialog0.dismiss();
                    }
                    takeOut=true;
                    exitFailDialog0 = CurrenySelectUtil.showExitFailDialog(this, new Callback() {
                        @Override
                        public void onDialogClick(int which, Dialog dialog) {
                            if (close&!isCovered){
                                dialog.dismiss();
                                SerialPortManager.instance().closeMaskDoor();
                                finish();
                            }else {
                                ToastUtil.show(PaperCurrencyDepositActivity.this,getResources().getString(R.string.please_take_out));
                            }

                        }
                    }, "0x16 "+getResources().getString(R.string.rejecting_pocket));

                } else if (((char) (received[7] & 0xff) == 0x17)) {

                    if (exitFailDialog1 != null && exitFailDialog1.isShowing()) {
                        exitFailDialog1.dismiss();
                    }

                    exitFailDialog1 = CurrenySelectUtil.showExitFailDialog(this, new Callback() {
                        @Override
                        public void onDialogClick(int which, Dialog dialog) {

                        }

                    }, "0x17 "+getResources().getString(R.string.rejecting_pocket));

                } else if (((char) (received[7] & 0xff) == 0x18)) {
                    SerialPortManager.instance().openMaskDoor();
                    ToastUtil.show(this, getResources().getString(R.string.draw_out));
                    LogPlus.e("read_thread", "0x18 置钞口有纸币，不能执行退出操作");

                }
            }
            break;
            case FINISH_DEPOSIT: {
                finish();
            }
            break;
            case SEARCH_LEAD: { // 查询退钞

                if (refundMoney==0){
                    refundMoney(received);
                    refundMoney++;
                }
//                ToastUtil.show(this, "查询退钞命令完成，结果待解析～");
            }
            break;
        }

    }

    private void refundMoney(byte[] received) {
        int num=0;
        String rejecting_information=null;
        int count = (received[7]+(received[8]<<8));
        for (int n = 0; n < count; n++) {
            int ERROR = (received[9+n*6]+(received[10+n*6]<<8))+(received[11+n*6]<<16)+(received[12+n*6]<<24);
            for (int i = 0; i < 32; i++) {
                if (((ERROR>>i)&0x1)==0x01){
                    switch (i){
                        case 0:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.no_magnetic);
                            break;
                        case 1:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.no_magnetic_error);
                            break;
                        case 2:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.length_width_is_not);
                            break;
                        case 3:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.results_are_inconsistent);
                            break;
                        case 4:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.UV_error);
                            break;
                        case 5:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.image_error);
                            break;
                        case 6:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.paper_money_others);
                            break;
                        case 7:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.no_edge_magnetic);
                            break;
                        case 8:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.version_is_not);
                            break;
                        case 9:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.bank_draft_is_not);
                            break;
                        case 10:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.face_is_not);
                            break;
                        case 11:
                            num=n+1;
                            i=32;
//                            rejecting_information= getResources().getString(R.string.a_paste);
                            break;
                        case 12:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.a_paste);
                            break;
                        case 13:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.too_old);
                            break;
                        case 14:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.incomplete);
                            break;
                        case 15:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.have_blot);
                            break;
                        case 16:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.broken_or_missing);
                            break;
                        case 17:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.image_authentication_error);
                            break;
                        case 18:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.edge_damage);
                            break;
                        case 19:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.magnetic_image_version_inconformity);
                            break;
                        case 20:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.magnetic_image_face_inconformity);
                            break;
                        case 21:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.currency_error);
                            break;
                        case 22:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.money_full);
                            break;
                        case 23:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.paper_money_tilt);
                            break;
                        case 24:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.spacing_is_too_small);
                            break;
                        case 25:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.transmission_error);
                            break;
                        case 26:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.debugging_mode);
                            break;
                        case 27:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.have_blot);
                            break;
                        case 28:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.error_in_data);
                            break;
                        case 29:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.serial_number_error);
                            break;
                        case 30:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.data_transmission_error);
                            break;
                        case 31:
                            num=n+1;
                            i=32;
                            rejecting_information= getResources().getString(R.string.the_infrared_transmission);
                            break;

                    }
                    refundMoneyBean = new RefundMoneyActivity.RefundMoneyBean(num,rejecting_information);
                    list.add(refundMoneyBean);
                }

            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void coverEvent(IsCoveringEvent isCoveringEvent){
        isCovered=isCoveringEvent.isCovering();
        if (!isCovered){
            tvStatus .setVisibility(View.GONE);
        }
        ToastUtil.show(this,isCovered+"");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CODE_DEPOSIT) {
            finish();
        }
    }

    public interface Callback {
        void onDialogClick(int which, Dialog dialog);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_paper_currency_deposit;
    }

    @Override
    public void initListener() {
        currency = SpzUtils.getString(Constant.PRINT_CURRENCY);
        if (!currency.isEmpty()){
            btnCurrency.setText(currency);
        }
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
        String string = SpzUtils.getString(Constant.PRINT_CURRENCY);
        if (string.isEmpty()){
            SerialPortManager.instance().sendCNRCommand();
            SpzUtils.putString(Constant.PRINT_CURRENCY,Constant.CNY);
            currency=Constant.CNY;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SerialPortManager.instance().sendSaveAck();
        SerialPortManager.instance().sendExitWorkModeCommand();
    }

}
