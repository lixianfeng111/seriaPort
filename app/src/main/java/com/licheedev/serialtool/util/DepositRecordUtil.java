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
        //其他存款
        SpzUtils.putInt("coin2",0);
        SpzUtils.putInt("cheque2",0);
        SpzUtils.putInt("paper_money2",0);
        SpzUtils.putInt("other2",0);
    }
}