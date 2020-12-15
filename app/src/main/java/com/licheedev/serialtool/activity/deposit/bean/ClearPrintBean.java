package com.licheedev.serialtool.activity.deposit.bean;

import com.licheedev.serialtool.activity.deposit.DepositDetailsActivity;

import java.util.List;

public class ClearPrintBean {
    private static List<DepositDetailsActivity.DepositDetailBean> listBeans;

    public ClearPrintBean(List<DepositDetailsActivity.DepositDetailBean> listBeans) {
        this.listBeans = listBeans;
    }

    public static List<DepositDetailsActivity.DepositDetailBean> getListBeans() {
        return listBeans;
    }

    public void setListBeans(List<DepositDetailsActivity.DepositDetailBean> listBeans) {
        this.listBeans = listBeans;
    }
    public static void clearList(){
        listBeans.clear();
    }
}
