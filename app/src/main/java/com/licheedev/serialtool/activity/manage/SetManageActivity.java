package com.licheedev.serialtool.activity.manage;

import android.app.ActivityManager;
import android.content.Intent;
import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.activity.manage.maintain.MaintainActivity;
import com.licheedev.serialtool.activity.manage.manage.ManageActivity;
import com.licheedev.serialtool.activity.manage.print.SystemPrintActivity;
import com.licheedev.serialtool.activity.manage.setting.SettingActivity;
import com.licheedev.serialtool.activity.manage.update.VersionUpdateActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LanguageUtils;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;

import butterknife.OnClick;

/**
 * 设置管理
 */
public class SetManageActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setmanager;
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

    @OnClick({R.id.ibtn_manager, R.id.ibtn_setting, R.id.ibtn_maintain, R.id.ibtn_print
            , R.id.ibtn_device_register, R.id.ibtn_update, R.id.ibtn_stop, R.id.ibtn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_manager:
                startActivity(new Intent(SetManageActivity.this, ManageActivity.class));
                break;
            case R.id.ibtn_setting:
                startActivity(new Intent(SetManageActivity.this, SettingActivity.class));
                break;
            case R.id.ibtn_maintain:
                startActivity(new Intent(SetManageActivity.this, MaintainActivity.class));
                break;
            case R.id.ibtn_print:
                startActivity(new Intent(SetManageActivity.this, SystemPrintActivity.class));
                break;
            case R.id.ibtn_device_register:
                startActivity(new Intent(SetManageActivity.this, DeviceRegisterActivity.class));
                break;
            case R.id.ibtn_update:
                startActivity(new Intent(SetManageActivity.this, VersionUpdateActivity.class));
                break;
            case R.id.ibtn_stop:
                finish();
                break;
            case R.id.ibtn_close:
                int currentVersion = android.os.Build.VERSION.SDK_INT;
                if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) {
                    Intent startMain =new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
                    System.exit(0);
                }else {// android2.1
                    ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                    am.restartPackage(getPackageName());
                }
//                AppManager.getAppManager().AppExit(this);
                break;
        }
    }

}
