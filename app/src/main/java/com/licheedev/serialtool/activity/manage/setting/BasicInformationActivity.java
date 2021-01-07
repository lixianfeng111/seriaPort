package com.licheedev.serialtool.activity.manage.setting;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;

public class BasicInformationActivity extends BaseActivity {

    private EditText client;
    private EditText site;
    private Button btnBack;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_basic_information;
    }

    //获取基本信息控件
    @Override
    protected void initView() {
        super.initView();
        client = findViewById(R.id.client);
        site = findViewById(R.id.site);
        btnBack = findViewById(R.id.btnBack);
    }

    @Override
    public void initListener() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取客户、地点、机器号、用户等控件的文本
                Editable text = client.getText();
                Editable text1 = site.getText();
                //判空
                if (TextUtils.isEmpty(text)||TextUtils.isEmpty(text1)){
                    ToastUtil.show(BasicInformationActivity.this,getResources().getString(R.string.please_complete_it));
                }else {//保存基本信息
                    SpzUtils.putString(Constant.CLIENT,text+"");
                    SpzUtils.putString(Constant.SITE,text1+"");
                    finish();
                }
            }
        });
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
}
