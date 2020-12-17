package com.licheedev.serialtool.activity.clear;

import android.app.Activity;
import android.os.Build;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.licheedev.serialtool.activity.deposit.DepositDetailsActivity;
import com.licheedev.serialtool.activity.deposit.OtherDepositActivity;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.TimeFormartUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import java.util.List;

public class TestFunction {
    private static String serial = Build.SERIAL;//HARDWARE
    public static Activity ctx = null;

    //清机测试打印
    public static void Test_Pos_SampleTicket_80MM_2(Activity activity, Pointer h) {

        int num=0,total=0,totalAll=0;
        int num100 = SpzUtils.getInt("num100", -1);
        int num50 = SpzUtils.getInt("num50", -1);
        int num20 = SpzUtils.getInt("num20", -1);
        int num10 = SpzUtils.getInt("num10", -1);
        int num5 = SpzUtils.getInt("num5", -1);
        int num1 = SpzUtils.getInt("num1", -1);
        int money100 = SpzUtils.getInt("money100", -1);
        int money50 = SpzUtils.getInt("money50", -1);
        int money20 = SpzUtils.getInt("money20", -1);
        int money10 = SpzUtils.getInt("money10", -1);
        int money5 = SpzUtils.getInt("money5", -1);
        int money1 = SpzUtils.getInt("money1", -1);
        //其他存款
        int coin2 = SpzUtils.getInt("coin2", 0);
        int cheque2 = SpzUtils.getInt("cheque2", 0);
        int paper_money2 = SpzUtils.getInt("paper_money2", 0);
        int other2 = SpzUtils.getInt("other2", 0);
        num=num1+num5+num10+num20+num50+num100;
        total=money1+money5+money10+money20+money50+money100;
        totalAll=total+coin2+cheque2+paper_money2+other2;
        {
            AutoReplyPrint.INSTANCE.CP_Printer_ClearPrinterBuffer(h);
            AutoReplyPrint.INSTANCE.CP_Pos_ResetPrinter(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteMode(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteEncoding(h, AutoReplyPrint.CP_MultiByteEncoding_UTF8);
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_PrintHorizontalLine(h, 0, 575);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintHorizontalLine(h, 0, 575);
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** 钞袋替换报告 ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("客户："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("client")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("地点："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("site")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("机器号："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(serial+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("用户："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("user")+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            boolean start = SpzUtils.getBoolean("start", false);
            if (start){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("开始："));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 130);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("timeDay2")));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 280);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("time2")+"\r\n"));
                SpzUtils.putBoolean("start",false);
            }else {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("开始："));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 130);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("timeDay")));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 280);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("time")+"\r\n"));
            }


            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("原钞袋ID"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 130);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("bagId")+"\r\n"));

            String old_lead_seal = SpzUtils.getString("old_lead_seal");
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("原封箱ID"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 130);
            if (!old_lead_seal.isEmpty()){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(old_lead_seal+"\r\n"));
            }else {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("lead_seal")+"\r\n"));
            }
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("结束："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 130);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 280);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("新钞袋ID"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 130);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("old_bagId")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("新封箱ID"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 130);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("new_lead_seal")+"\r\n"));

        }

//        {
//            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
//            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("完成存款整数："));
//            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
//            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("2\r\n"));
//
//            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("成功上载整数："));
//            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
//            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("2\r\n"));
//
//            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("不成功上载整数："));
//            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
//            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("0\r\n"));
//        }
        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("钞票：CNY\r\n"));
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("Banknote\r\n"));
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("面额"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("张"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("100"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num100+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money100+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("50"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num50+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money50+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("20"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num20+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money20+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("10"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num10+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money10+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("5"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num5+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money5+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("1"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num1+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money1+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("总数"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(total+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他：\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("钞票"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+paper_money2+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("硬币"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+coin2+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("支票"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+cheque2+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+other2+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("STC 总计：\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 10);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(totalAll+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** 完 ***\r\n\r\n\r\n"));
            AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
        }

    }

    //存款报告打印
    public static void deposit_Print_SampleTicket(Activity activity, List<DepositDetailsActivity.DepositDetailBean> list, Pointer h){
        int total=0;
        int num=0;
        int num100 = SpzUtils.getInt("num100", 0);
        int num50 = SpzUtils.getInt("num50", 0);
        int num20 = SpzUtils.getInt("num20", 0);
        int num10 = SpzUtils.getInt("num10", 0);
        int num5 = SpzUtils.getInt("num5", 0);
        int num1 = SpzUtils.getInt("num1", 0);
        int money100 = SpzUtils.getInt("money100", 0);
        int money50 = SpzUtils.getInt("money50", 0);
        int money20 = SpzUtils.getInt("money20", 0);
        int money10 = SpzUtils.getInt("money10", 0);
        int money5 = SpzUtils.getInt("money5", 0);
        int money1 = SpzUtils.getInt("money1", 0);
        int save_num100=0,save_num50=0,save_num20=0,save_num10=0,save_num5=0,save_num1=0;
        int save_money100=0,save_money50=0,save_money20=0,save_money10=0,save_money5=0,save_money1=0;

        int coin2 = SpzUtils.getInt("coin2", 0);
        int cheque2 = SpzUtils.getInt("cheque2", 0);
        int paper_money2 = SpzUtils.getInt("paper_money2", 0);
        int other2 = SpzUtils.getInt("other2", 0);

        coin2+=coin;
        cheque2+=cheque;
        paper_money2+=paper_money;
        other2+=other;
        {
            AutoReplyPrint.INSTANCE.CP_Printer_ClearPrinterBuffer(h);
            AutoReplyPrint.INSTANCE.CP_Pos_ResetPrinter(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteMode(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteEncoding(h, AutoReplyPrint.CP_MultiByteEncoding_UTF8);
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_PrintHorizontalLine(h, 0, 575);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintHorizontalLine(h, 0, 575);
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** 存款报告 ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("客户："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("client")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("地点："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("site")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("存款类别："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("钞票\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("机器号："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(serial+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("用户："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("user")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("币种：CNY\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("钞票\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("面额"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("张"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            if (list!=null){
                for (int i = 0; i <list.size() ; i++) {
                    DepositDetailsActivity.DepositDetailBean depositDetailBean = list.get(i);
                    if (depositDetailBean.deposit==100){
                        save_money100+=depositDetailBean.sum;
                        save_num100+=depositDetailBean.count;
                        total+=depositDetailBean.sum;
                        num+=depositDetailBean.count;
                        num100+=depositDetailBean.count;
                        money100+=depositDetailBean.sum;

                    }else if (depositDetailBean.deposit==50){
                        save_money50+=depositDetailBean.sum;
                        save_num50+=depositDetailBean.count;
                        total+=depositDetailBean.sum;
                        num+=depositDetailBean.count;
                        num50+=depositDetailBean.count;
                        money50+=depositDetailBean.sum;

                    }else if (depositDetailBean.deposit==20){
                        save_money20+=depositDetailBean.sum;
                        save_num20+=depositDetailBean.count;
                        total+=depositDetailBean.sum;
                        num+=depositDetailBean.count;
                        num20+=depositDetailBean.count;
                        money20+=depositDetailBean.sum;

                    }else if (depositDetailBean.deposit==10){
                        save_money10+=depositDetailBean.sum;
                        save_num10+=depositDetailBean.count;
                        total+=depositDetailBean.sum;
                        num+=depositDetailBean.count;
                        num10+=depositDetailBean.count;
                        money10+=depositDetailBean.sum;

                    }else if (depositDetailBean.deposit==5){
                        save_money5+=depositDetailBean.sum;
                        save_num5+=depositDetailBean.count;
                        total+=depositDetailBean.sum;
                        num+=depositDetailBean.count;
                        num5+=depositDetailBean.count;
                        money5+=depositDetailBean.sum;

                    }else if (depositDetailBean.deposit==1){
                        save_money1+=depositDetailBean.sum;
                        save_num1+=depositDetailBean.count;
                        total+=depositDetailBean.sum;
                        num+=depositDetailBean.count;
                        num1+=depositDetailBean.count;
                        money1+=depositDetailBean.sum;

                    }
                }
            }
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("100"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_num100+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_money100+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("50"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_num50+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_money50+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("20"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_num20+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_money20+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("10"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_num10+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_money10+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("5"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_num5+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_money5+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("1"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_num1+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_money1+"\r\n"));

            {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("总数"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num+""));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(total+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetTextUnderline(h,500);
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他：\r\n"));
            }

            {

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("硬币\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+coin+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("支票\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+cheque+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("钞票\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+paper_money+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+other+"\r\n"));

            }

            {
                total=total+coin+cheque+paper_money+other;
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("STC 总计："));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(total+""));
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** 完 ***"));
                AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            }

            {
                SpzUtils.putInt("num100",num100);
                SpzUtils.putInt("num50",num50);
                SpzUtils.putInt("num20",num20);
                SpzUtils.putInt("num10",num10);
                SpzUtils.putInt("num5",num5);
                SpzUtils.putInt("num1",num1);
                SpzUtils.putInt("money100",money100);
                SpzUtils.putInt("money50",money50);
                SpzUtils.putInt("money20",money20);
                SpzUtils.putInt("money10",money10);
                SpzUtils.putInt("money5",money5);
                SpzUtils.putInt("money1",money1);
                //其他存款
                SpzUtils.putInt("coin2",coin2);
                SpzUtils.putInt("cheque2",cheque2);
                SpzUtils.putInt("paper_money2",paper_money2);
                SpzUtils.putInt("other2",other2);
                coin=0;
                cheque=0;
                paper_money=0;
                other=0;
            }
        }
    }


    private static int coin,cheque,paper_money,other;
    //选择存款报告打印
    public static void select_deposit_Print_SampleTicket(Activity activity,int n, Pointer h){

        int how_much = SpzUtils.getInt("how_much", -1);

        {
            AutoReplyPrint.INSTANCE.CP_Printer_ClearPrinterBuffer(h);
            AutoReplyPrint.INSTANCE.CP_Pos_ResetPrinter(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteMode(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteEncoding(h, AutoReplyPrint.CP_MultiByteEncoding_UTF8);
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_PrintHorizontalLine(h, 0, 575);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintHorizontalLine(h, 0, 575);
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** 其他存款 ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("客户："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("client")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("地点："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("site")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("存款类别："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("硬币\r\n"));
            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("支票\r\n"));
            } else if (n == 2) {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("钞票\r\n"));
            }else {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他\r\n"));
            }

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("机器号："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(serial+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("用户："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("user")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));
        }
        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他：\r\n"));
        }
        {
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("硬币\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+how_much+"\r\n"));
                coin+=how_much;
            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("支票\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+how_much+"\r\n"));
                cheque+=how_much;
            }else if (n==2){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("钞票\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+how_much+"\r\n"));
                paper_money+=how_much;
            }else if (n==3){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+how_much+"\r\n"));
                other+=how_much;
            }
        }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("STC 总计："));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(how_much+""));
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** 完 ***"));
                AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            }
        }

    //记录存款报告打印
    public static void record_deposit_Print_SampleTicket(Activity activity,int n,int money, Pointer h) {

        {
            AutoReplyPrint.INSTANCE.CP_Printer_ClearPrinterBuffer(h);
            AutoReplyPrint.INSTANCE.CP_Pos_ResetPrinter(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteMode(h);
            AutoReplyPrint.INSTANCE.CP_Pos_SetMultiByteEncoding(h, AutoReplyPrint.CP_MultiByteEncoding_UTF8);
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_PrintHorizontalLine(h, 0, 575);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintHorizontalLine(h, 0, 575);
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** 其他存款 ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("客户："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("client")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("地点："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("site")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("存款类别："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("硬币\r\n"));
            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("支票\r\n"));
            } else if (n == 2) {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("钞票\r\n"));
            }else {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他\r\n"));
            }

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("机器号："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(serial+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("用户："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("user")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他：\r\n"));
        }

        {
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("硬币\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+money+"\r\n"));

            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("支票\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+money+"\r\n"));

            }else if (n==2){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("钞票\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+money+"\r\n"));

            }else if (n==3){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("种类"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("其他\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("金额"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("CNY "+money+"\r\n"));

            }

        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("STC 总计："));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money+""));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** 完 ***"));
            AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
        }
        }
}
