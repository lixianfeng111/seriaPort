package com.licheedev.serialtool.activity.deposit;

import android.content.Intent;
import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.SerialPortManager2;
import com.licheedev.serialtool.util.SpzUtils;

import butterknife.OnClick;

/**
 * 选择存款方式
 */
public class SelectDepositActivitys extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_deposit;
    }


    @Override
    protected void initView() {
            SerialPortManager2.instance().close();
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
                intent.putExtra("iscurrent", false);
                startActivity(intent);
                break;
            case R.id.button:
                finish();
                break;
        }
    }

}
