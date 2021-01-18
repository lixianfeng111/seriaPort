package com.licheedev.serialtool.util.constant;

public interface Constant {
    /**
     * key
     */
    //LoginActivity
    String USER = "user";
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
    // 人民币各个面值的张数
    String NUM100 = "num100";
    String NUM50 = "num50";
    String NUM20 = "num20";
    String NUM10 = "num10";
    String NUM5 = "num5";
    String NUM1 = "num1";
    // 墨西哥币各个面值的张数
    String NUM_MXN1000 = "num_max1000";
    String NUM_MXN500 = "num_max500";
    String NUM_MXN200 = "num_max200";
    String NUM_MXN100 = "num_max100";
    String NUM_MXN50 = "num_max50";
    String NUM_MXN20 = "num_max20";
    //人民币各面值总金额
    String MONEY100 = "money100";
    String MONEY50 = "money50";
    String MONEY20 = "money20";
    String MONEY10 = "money10";
    String MONEY5 = "money5";
    String MONEY1 = "money1";
    //墨西哥各面值总金额
    String MONEY_MXN1000 = "money_max1000";
    String MONEY_MXN500 = "money_max500";
    String MONEY_MXN200 = "money_max200";
    String MONEY_MXN100 = "money_max100";
    String MONEY_MXN50 = "money_max50";
    String MONEY_MXN20 = "money_max20";

    //其他存款
    String COIN2 = "coin2";
    String CHEQUE2 = "cheque2";
    String PAPER_MONEY2 = "paper_money2";
    String OTHER2 = "other2";

    //其他存款
    String COIN2_MXN = "coin2_max";
    String CHEQUE2_MXN = "cheque2_max";
    String PAPER_MONEY2_MXN = "paper_money2_max";
    String OTHER2_MXN = "other2_max";
    //打印和基本信息设置页
    String CLIENT = "client";
    String SITE = "site";
    //打印和清机测试页ClearDeviceTestActivity
    String BAG_ID = "bagId";
    String OLD_LEAD_SEAL = "old_lead_seal";
    String LEAD_SEAL = "lead_seal";
    String OLD_BAG_ID = "old_bagId";
    String NEW_LEAD_SEAL = "new_lead_seal";
    //其他存款输入金额数
    String HOW_MUCH = "how_much";
    //用于存款记录
    String MONEY_RECORD = "money_record";
    String CURRENCY_RECORD = "currency_record";

    String IS_SAVED = "isSaved";

    String IS_CURRENT = "iscurrent";
    //系统信息SN
    String SN = "SN";

    //墨西哥币种
    String MXN = "MXN";
    //人民币
    String CNR = "CNR";
    //打印币种
    String PRINT_CURRENCY = "print_currency";
    String OUT_OF_THIS="out_of_this";
    String Banknote = "Banknote";
    String STC = "STC";
}
