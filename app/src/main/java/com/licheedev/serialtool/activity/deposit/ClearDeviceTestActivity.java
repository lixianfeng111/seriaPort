package com.licheedev.serialtool.activity.deposit;

import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.clear.ClearDeviceHintActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.SerialPortManager2;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 清机测试
 */
public class ClearDeviceTestActivity extends BaseActivity {

    @BindView(R.id.tvOriginalNum)
    TextView tvOriginalNum;
    @BindView(R.id.tvNewNum)
    EditText tvNewNum;
    @BindView(R.id.tvLead)
    EditText tvLead;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clear_device;
    }

    @Override
    protected void initView() {
        super.initView();
        SerialPortManager.instance().close();
        SerialPortManager2.instance().initDevice();
        //获取原钞袋号
        String bag = SpzUtils.getString(Constant.OLD_BAG_ID);
        String old_lead_seal = SpzUtils.getString(Constant.OLD_LEAD_SEAL);
        if (!TextUtils.isEmpty(bag)){
            tvOriginalNum.setText(bag);
            SpzUtils.putString(Constant.BAG_ID,bag);
        }
        if (!TextUtils.isEmpty(old_lead_seal)){
            SpzUtils.putString(Constant.LEAD_SEAL,old_lead_seal);
        }
        //设置只能输入数字
        tvLead.setInputType( InputType.TYPE_CLASS_NUMBER);
        tvNewNum.setInputType( InputType.TYPE_CLASS_NUMBER);
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

    @OnClick({R.id.ibtn_back, R.id.ibtn_ok, R.id.ibtn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_back:

                break;
            case R.id.ibtn_ok:
                //获取本次钞袋号并保存
                Editable text1 =  tvNewNum.getText();
                SpzUtils.putString(Constant.OLD_BAG_ID,text1+"");
                //获取本次封铅号
                Editable text2 = tvLead.getText();
                SpzUtils.putString(Constant.OLD_LEAD_SEAL,SpzUtils.getString(Constant.NEW_LEAD_SEAL));
                SpzUtils.putString(Constant.NEW_LEAD_SEAL,text2+"");
                if (TextUtils.isEmpty(text1)){
                    ToastUtil.show(this,getResources().getString(R.string.new_bag_id));
                }else if (TextUtils.isEmpty(text2)){
                    ToastUtil.show(this,getResources().getString(R.string.lead_seal));
                }else {
                    startActivity(new Intent(this, ClearDeviceHintActivity.class));
                    finish();
                }
                break;
            case R.id.ibtn_cancel:
                finish();
                break;
        }
    }

}
