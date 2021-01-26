package com.licheedev.serialtool.activity.deposit;

import android.content.Intent;
import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.SerialPortManager2;
import com.licheedev.serialtool.comn.SerialReadThread2;
import com.licheedev.serialtool.comn.event.ClearEvent;
import com.licheedev.serialtool.comn.event.DepositEvent;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.constant.Constant;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

/**
 * 选择存款方式
 */
public class SelectDepositActivitys extends BaseActivity {

    int[] command8=new  int[]{0xA1,0xA2,0xA3,0xA4,0x04,0x00,0x90,0xBB,0xBB,0x90};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_deposit;
    }


    @Override
    protected void initView() {
//            SerialPortManager2.instance().close();
            SerialPortManager.instance().initDevice();
        super.initView();
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
        String string = SpzUtils.getString(Constant.SN);
        if (string.isEmpty()){
            SerialPortManager.instance().sendCommand(SerialPortManager.byteArrayToHexString(command8));
        }
    }

    @OnClick({R.id.ibtn_paper_select, R.id.ibtn_other_select, R.id.ibtn_record,R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_paper_select:
                startActivity(new Intent(this, PaperCurrencyDepositActivity.class));
                break;
            case R.id.ibtn_other_select:
                startActivity(new Intent(this, OtherDepositActivity.class));
                break;
            case R.id.ibtn_record:
                Intent intent = new Intent(this, DepositManageActivity.class);
                intent.putExtra(Constant.IS_CURRENT, false);
                startActivity(intent);
                break;
            case R.id.button:
                finish();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void depositEvent(DepositEvent depositEvent){
        SpzUtils.putString(Constant.SN,depositEvent.getSystem_info());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SerialPortManager.instance().close();
    }
}
