package com.licheedev.serialtool.activity.deposit;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.clear.ClearDeviceHintActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.SerialPortManager2;
import com.licheedev.serialtool.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 清机测试
 */
public class ClearDeviceTestActivity extends BaseActivity {

    @BindView(R.id.tvOriginalNum)
    EditText tvOriginalNum;
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
                Editable text = tvOriginalNum.getText();
                Editable text1 = tvNewNum.getText();
                Editable text2 = tvLead.getText();
                if (TextUtils.isEmpty(text)){
                    ToastUtil.show(this,"原钞袋号为空");
                }else if (TextUtils.isEmpty(text1)){
                    ToastUtil.show(this,"新钞袋号为空");
                }else if (TextUtils.isEmpty(text2)){
                    ToastUtil.show(this,"封铅号为空");
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
