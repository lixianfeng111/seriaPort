package com.licheedev.serialtool.activity.manage.update;

import android.view.View;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.base.BaseActivity;

import butterknife.OnClick;

/**
 * 版本更新
 */
public class VersionUpdateActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_version_update;
    }

    @OnClick({R.id.tvupdateset, R.id.tvupdateandroid, R.id.btnBack, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvupdateset:
                break;

            case R.id.tvupdateandroid:
                break;

            case R.id.btnBack:
                finish();
                break;

            case R.id.btLogout:
                break;


        }
    }

}
