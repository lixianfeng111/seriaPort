package com.licheedev.serialtool.activity.deposit;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 存款方式选择
 */
public class OtherDepositActivity extends BaseActivity {

    @BindView(R.id.editText)
    EditText editText;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other_deposit;
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

    @OnClick({R.id.ibtn_ok, R.id.ibtn_cancel, R.id.btnBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ibtn_ok:
                startActivity(new Intent(OtherDepositActivity.this, DepositManageActivity.class));
                break;
            case R.id.ibtn_cancel:
                startActivity(new Intent(OtherDepositActivity.this, DepositDetailsActivity.class));
                break;
            case R.id.btnBack:
                finish();
                break;

        }
    }

}
