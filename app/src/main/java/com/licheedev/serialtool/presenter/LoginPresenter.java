package com.licheedev.serialtool.presenter;

import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.base.CommonPresent;
import com.licheedev.serialtool.base.IBaseView;
import com.licheedev.serialtool.bean.LoginBean;

import java.util.Map;

public class LoginPresenter extends CommonPresent<LoginBean> {
    public LoginPresenter(IBaseView<LoginBean> loginBeanIBaseView) {
        super(loginBeanIBaseView);
    }
    public void getLogin(Map<String,Object> params){
        doPost("",params,new LoginBean());
    }
}
