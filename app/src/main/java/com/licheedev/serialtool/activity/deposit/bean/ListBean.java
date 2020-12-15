package com.licheedev.serialtool.activity.deposit.bean;


import com.licheedev.serialtool.activity.deposit.DepositDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class ListBean  {
//    public static List<DepositDetailsActivity.DepositDetailBean> list2=new ArrayList<>();
    public static List<DepositDetailsActivity.DepositDetailBean> list=new ArrayList<>();
    private static ListBean instance;

    public ListBean(List<DepositDetailsActivity.DepositDetailBean> list) {
        this.list = list;
    }

    public static List<DepositDetailsActivity.DepositDetailBean> getList() {
        return list;
    }

    public void setList(List<DepositDetailsActivity.DepositDetailBean> list) {
        this.list = list;
    }

    public static void clearList(){
        list.clear();
    }
    public static ListBean getInstance(){
        if (instance==null){
            instance = new ListBean(list);
        }
        return instance;
    }

}
