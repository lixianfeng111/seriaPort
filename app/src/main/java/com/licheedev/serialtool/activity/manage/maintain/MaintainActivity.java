package com.licheedev.serialtool.activity.manage.maintain;

import android.content.Intent;
import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LogOutUtil;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.TimeFormartUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;

import butterknife.OnClick;

/**
 * 维护
 */
public class MaintainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_maintain;
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

    @OnClick({R.id.tvSysinfo, R.id.tvDeviceSet, R.id.tvDebug, R.id.tvControl
            , R.id.tvErrorClear, R.id.tvbillinit, R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSysinfo:
                startActivity(new Intent(this, SystemInfoActivity.class));
                break;
            case R.id.tvDeviceSet:
                startActivity(new Intent(this, DeviceSetActivity.class));
                break;
            case R.id.tvDebug:
                startActivity(new Intent(this, DebugActivity.class));
                break;
            case R.id.tvControl:
                startActivity(new Intent(this, DeviceControlActivity.class));
                break;
            case R.id.tvErrorClear:
                startActivity(new Intent(this, DepositErrorActivity.class));
                break;
            case R.id.tvbillinit:
                SpzUtils.putString(Constant.OLD_BAG_ID,Constant.ZERO);
                SpzUtils.putString(Constant.OLD_LEAD_SEAL,Constant.ZERO);
                SpzUtils.putString(Constant.NEW_LEAD_SEAL,Constant.ZERO);
                SpzUtils.putString(Constant.timeDay2, TimeFormartUtils.getTimeDay());
                SpzUtils.putString(Constant.TIME2,TimeFormartUtils.getTime());
                SpzUtils.putBoolean(Constant.START,true);
                SpzUtils.putString(Constant.LEAD_SEAL,"");
                ToastUtil.show(this,getResources().getString(R.string.initialization));
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.btLogout:
                LogOutUtil.LogOut(this, LoginActivity.class);
                finish();
                break;
        }
    }

}
