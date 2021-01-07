package com.licheedev.serialtool.util.constant;

public interface Constant {
    /**
     * key
     */
    //LoginActivity
    String LANGUAGE = "language";
    String IS_PRINT = "isPrint";//是否打印
    String LIST= "list";//存款list
    String NUM = "num";//金额
    String COUNTS = "counts";//张数
    String CI = "ci";
    //MainActivity
    String PORT = "/dev/ttyS3";//打印串口
    int BAUD_RATE = 9600;//打印波特率
    String BAUD_RATE_String = "9600";
    String PORT_MONEY = "/dev/ttyS4";//存钱串口
    String BAUD_RATE_MONEY = "115200";//存钱波特率
    String PORT_ON_THE_BACK = "/dev/ttyS1";//背面串口测试
    //清机
    String START = "start";
    String old_timeDay = "old_timeDay";
    String timeDay2 = "timeDay2";
    String OLD_TIME = "old_time";
    String TIME2 = "time2";
    String TIME = "time";
    String timeDay = "timeDay";
    /**
     * 打印 TestFunction
     */
    //清机打印
// 各个面值的张数
    String NUM100 = "num100";
    String NUM50 = "num50";
    String NUM20 = "num20";
    String NUM10 = "num10";
    String NUM5 = "num5";
    String NUM1 = "num1";
    //各面值总金额
    String MONEY100 = "money100";
    String MONEY50 = "money50";
    String MONEY20 = "money20";
    String MONEY10 = "money10";
    String MONEY5 = "money5";
    String MONEY1 = "money1";
    //其他存款
    String COIN2 = "coin2";
    String CHEQUE2 = "cheque2";
    String PAPER_MONEY2 = "paper_money2";
    String OTHER2 = "other2";
    //打印和基本信息设置页
    String CLIENT = "client";
    String SITE = "site";
    String USER = "user";
    //打印和清机测试页ClearDeviceTestActivity
    String BAG_ID = "bagId";
    String OLD_LEAD_SEAL = "old_lead_seal";
    String LEAD_SEAL = "lead_seal";
    String OLD_BAG_ID = "old_bagId";
    String NEW_LEAD_SEAL = "new_lead_seal";

}
