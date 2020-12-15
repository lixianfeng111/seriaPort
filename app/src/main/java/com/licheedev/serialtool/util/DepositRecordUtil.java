package com.licheedev.serialtool.util;

public class DepositRecordUtil {
    public static void saveDepositRecord(){
        SpzUtils.putInt("num100",0);
        SpzUtils.putInt("num50",0);
        SpzUtils.putInt("num20",0);
        SpzUtils.putInt("num10",0);
        SpzUtils.putInt("num5",0);
        SpzUtils.putInt("num1",0);
        SpzUtils.putInt("money100",0);
        SpzUtils.putInt("money50",0);
        SpzUtils.putInt("money20",0);
        SpzUtils.putInt("money10",0);
        SpzUtils.putInt("money5",0);
        SpzUtils.putInt("money1",0);
        SpzUtils.putInt("ci",1);
    }
}
