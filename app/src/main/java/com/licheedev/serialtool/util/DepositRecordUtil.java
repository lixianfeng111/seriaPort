package com.licheedev.serialtool.util;

import com.licheedev.serialtool.util.constant.Constant;

public class DepositRecordUtil {
    public static void saveDepositRecord(){
        SpzUtils.putInt(Constant.NUM100,0);
        SpzUtils.putInt(Constant.NUM50,0);
        SpzUtils.putInt(Constant.NUM20,0);
        SpzUtils.putInt(Constant.NUM10,0);
        SpzUtils.putInt(Constant.NUM5,0);
        SpzUtils.putInt(Constant.NUM1,0);

        SpzUtils.putInt(Constant.NUM_MXN1000,0);
        SpzUtils.putInt(Constant.NUM_MXN500,0);
        SpzUtils.putInt(Constant.NUM_MXN200,0);
        SpzUtils.putInt(Constant.NUM_MXN100,0);
        SpzUtils.putInt(Constant.NUM_MXN50,0);
        SpzUtils.putInt(Constant.NUM_MXN20,0);

        SpzUtils.putInt(Constant.MONEY100,0);
        SpzUtils.putInt(Constant.MONEY50,0);
        SpzUtils.putInt(Constant.MONEY20,0);
        SpzUtils.putInt(Constant.MONEY10,0);
        SpzUtils.putInt(Constant.MONEY5,0);
        SpzUtils.putInt(Constant.MONEY1,0);

        SpzUtils.putInt(Constant.MONEY_MXN1000,0);
        SpzUtils.putInt(Constant.MONEY_MXN500,0);
        SpzUtils.putInt(Constant.MONEY_MXN200,0);
        SpzUtils.putInt(Constant.MONEY_MXN100,0);
        SpzUtils.putInt(Constant.MONEY_MXN50,0);
        SpzUtils.putInt(Constant.MONEY_MXN20,0);
        //其他存款
        SpzUtils.putInt(Constant.COIN2,0);
        SpzUtils.putInt(Constant.CHEQUE2,0);
        SpzUtils.putInt(Constant.PAPER_MONEY2,0);
        SpzUtils.putInt(Constant.OTHER2,0);

        SpzUtils.putInt(Constant.COIN2_MXN,0);
        SpzUtils.putInt(Constant.CHEQUE2_MXN,0);
        SpzUtils.putInt(Constant.PAPER_MONEY2_MXN,0);
        SpzUtils.putInt(Constant.OTHER2_MXN,0);
    }
}
