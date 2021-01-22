package com.licheedev.serialtool.activity.clear;

import android.app.Activity;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.deposit.DepositDetailsActivity;
import com.licheedev.serialtool.util.LogPlus;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.TimeFormartUtils;
import com.licheedev.serialtool.util.constant.Constant;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import java.util.List;

public class TestFunction {
//    private static String serial = Build.SERIAL;//HARDWARE
    public static Activity activity2;

    //清机测试打印
    public static void Test_Pos_SampleTicket_80MM(Activity activity, Pointer h) {
        activity2=activity;
        int num=0,total=0,num_mxn=0,total_mxn=0,totalAll=0,totalAll_mxn=0;
        //获取人民币各个面值的张数
        int num100 = SpzUtils.getInt(Constant.NUM100, 0);
        int num50 = SpzUtils.getInt(Constant.NUM50, 0);
        int num20 = SpzUtils.getInt(Constant.NUM20, 0);
        int num10 = SpzUtils.getInt(Constant.NUM10, 0);
        int num5 = SpzUtils.getInt(Constant.NUM5, 0);
        int num1 = SpzUtils.getInt(Constant.NUM1, 0);
        //获取墨西哥各个面值的张数
        int num_mxn1000 = SpzUtils.getInt(Constant.NUM_MXN1000, 0);
        int num_mxn500 = SpzUtils.getInt(Constant.NUM_MXN500, 0);
        int num_mxn200 = SpzUtils.getInt(Constant.NUM_MXN200, 0);
        int num_mxn100 = SpzUtils.getInt(Constant.NUM_MXN100, 0);
        int num_mxn50 = SpzUtils.getInt(Constant.NUM_MXN50, 0);
        int num_mxn20 = SpzUtils.getInt(Constant.NUM_MXN20, 0);
        //获取各面值总金额
        int money100 = SpzUtils.getInt(Constant.MONEY100, 0);
        int money50 = SpzUtils.getInt(Constant.MONEY50, 0);
        int money20 = SpzUtils.getInt(Constant.MONEY20, 0);
        int money10 = SpzUtils.getInt(Constant.MONEY10, 0);
        int money5 = SpzUtils.getInt(Constant.MONEY5, 0);
        int money1 = SpzUtils.getInt(Constant.MONEY1, 0);
        //获取各面值总金额
        int money_mxn1000 = SpzUtils.getInt(Constant.MONEY_MXN1000, 0);
        int money_mxn500 = SpzUtils.getInt(Constant.MONEY_MXN500, 0);
        int money_mxn200 = SpzUtils.getInt(Constant.MONEY_MXN200, 0);
        int money_mxn100 = SpzUtils.getInt(Constant.MONEY_MXN100, 0);
        int money_mxn50 = SpzUtils.getInt(Constant.MONEY_MXN50, 0);
        int money_mxn20 = SpzUtils.getInt(Constant.MONEY_MXN20, 0);
        //获取其他存款
        int coin2 = SpzUtils.getInt(Constant.COIN2, 0);
        int cheque2 = SpzUtils.getInt(Constant.CHEQUE2, 0);
        int paper_money2 = SpzUtils.getInt(Constant.PAPER_MONEY2, 0);
        int other2 = SpzUtils.getInt(Constant.OTHER2, 0);
        //获取墨西哥币其他存款
        int coin2_mxn = SpzUtils.getInt(Constant.COIN2_MXN, 0);
        int cheque2_mxn = SpzUtils.getInt(Constant.CHEQUE2_MXN, 0);
        int paper_money2_mxn = SpzUtils.getInt(Constant.PAPER_MONEY2_MXN, 0);
        int other2_mxn = SpzUtils.getInt(Constant.OTHER2_MXN, 0);
        //得到钞票总张数
        num=num1+num5+num10+num20+num50+num100;
        //得到钞票总金额
        total=money1+money5+money10+money20+money50+money100;
        //总计
        totalAll=total+coin2+cheque2+paper_money2+other2;
        //得到墨西哥币钞票总张数
        num_mxn=num_mxn20+num_mxn50+num_mxn100+num_mxn200+num_mxn500+num_mxn1000;
        //得到墨西哥币钞票总金额
        total_mxn=money_mxn20+money_mxn50+money_mxn100+money_mxn200+money_mxn500+money_mxn1000;
        //得到墨西哥币总计
        totalAll_mxn=total_mxn+coin2_mxn+cheque2_mxn+paper_money2_mxn+other2_mxn;
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
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.replace_money_bag)+" ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.client_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.CLIENT)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.site_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.SITE)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.machine_No_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.SN)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.user_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.USER)+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.start_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 130);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.old_timeDay)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 280);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.OLD_TIME)+"\r\n"));


            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.old_money_bag_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 280);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.BAG_ID)+"\r\n"));
            String old_lead_seal = SpzUtils.getString(Constant.OLD_LEAD_SEAL);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.old_sealing_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 280);
            if (!old_lead_seal.isEmpty()){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(old_lead_seal+"\r\n"));
            }else {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.LEAD_SEAL)+"\r\n"));
            }
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.end_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 130);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 280);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.new_money_bag_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 280);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.OLD_BAG_ID)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.new_sealing_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 280);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.NEW_LEAD_SEAL)+"\r\n"));

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
            if (num>0){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.currency_print)+Constant.CNY +"\r\n"));
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.Banknote+"\r\n"));
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.face_value)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.piece)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)+"\r\n"));

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

                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.amount_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num+""));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(total+"\r\n"));
            }else if (num_mxn>0){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.currency_print)+"MXN\r\n"));
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.Banknote+"\r\n"));
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.face_value)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.piece)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("1000"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num_mxn1000+""));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money100+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("500"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num_mxn500+""));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money_mxn500+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("200"));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num_mxn200+""));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money_mxn200+"\r\n"));

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

                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.amount_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num+""));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(total+"\r\n"));
            }

        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other_print)+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            if (paper_money2>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+paper_money2+"\r\n"));
            }else if (coin2>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+coin2+"\r\n"));
            }else if (cheque2>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+cheque2+"\r\n"));
            }else if (other2>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+other2+"\r\n"));
            }else if (paper_money2_mxn>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+paper_money2_mxn+"\r\n"));
            }else if (coin2_mxn>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+coin2_mxn+"\r\n"));
            }else if (cheque2_mxn>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+cheque2_mxn+"\r\n"));
            }else if (other2_mxn>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 20);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+other2_mxn+"\r\n"));
            }

        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.STC+" "+content(R.string.total_print)+"\r\n"));
        }

        {
            if (totalAll>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 10);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(totalAll+"\r\n"));
            }else if (totalAll_mxn>0){
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 10);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 100);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(totalAll_mxn+"\r\n"));
            }

            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.finish_print)+" ***\r\n\r\n\r\n"));
            AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
        }

    }


    //存款报告打印
    public static void deposit_Print_SampleTicket(Activity activity, List<DepositDetailsActivity.DepositDetailBean> list, Pointer h){
        activity2=activity;
        int total=0;
        int num=0;
        int num100 = SpzUtils.getInt(Constant.NUM100, 0);
        int num50 = SpzUtils.getInt(Constant.NUM50, 0);
        int num20 = SpzUtils.getInt(Constant.NUM20, 0);
        int num10 = SpzUtils.getInt(Constant.NUM10, 0);
        int num5 = SpzUtils.getInt(Constant.NUM5, 0);
        int num1 = SpzUtils.getInt(Constant.NUM1, 0);

        int money100 = SpzUtils.getInt(Constant.MONEY100, 0);
        int money50 = SpzUtils.getInt(Constant.MONEY50, 0);
        int money20 = SpzUtils.getInt(Constant.MONEY20, 0);
        int money10 = SpzUtils.getInt(Constant.MONEY10, 0);
        int money5 = SpzUtils.getInt(Constant.MONEY5, 0);
        int money1 = SpzUtils.getInt(Constant.MONEY1, 0);
        int save_num100=0,save_num50=0,save_num20=0,save_num10=0,save_num5=0,save_num1=0;
        int save_money100=0,save_money50=0,save_money20=0,save_money10=0,save_money5=0,save_money1=0;

        int coin2 = SpzUtils.getInt(Constant.COIN2, 0);
        int cheque2 = SpzUtils.getInt(Constant.CHEQUE2, 0);
        int paper_money2 = SpzUtils.getInt(Constant.PAPER_MONEY2, 0);
        int other2 = SpzUtils.getInt(Constant.OTHER2, 0);
        //将其他存款数累加
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
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.deposit_report)+" ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.client_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.CLIENT)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.site_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.SITE)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.deposit_category_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.machine_No_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.SN)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.user_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.USER)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.currency_print)+"CNY\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.banknotes_print)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.face_value)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.piece)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)+"\r\n"));
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
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.amount_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num+""));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(total+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetTextUnderline(h,500);
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other_print)+"\r\n"));
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+coin+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+cheque+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+paper_money+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+other+"\r\n"));

            }

            {
                total=total+coin+cheque+paper_money+other;
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.STC+" "+content(R.string.total_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(total+""));
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.finish_print)+" ***"));
                AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            }

            {

                SpzUtils.putInt(Constant.NUM100,num100);
                SpzUtils.putInt(Constant.NUM50,num50);
                SpzUtils.putInt(Constant.NUM20,num20);
                SpzUtils.putInt(Constant.NUM10,num10);
                SpzUtils.putInt(Constant.NUM5,num5);
                SpzUtils.putInt(Constant.NUM1,num1);

                SpzUtils.putInt(Constant.MONEY100,money100);
                SpzUtils.putInt(Constant.MONEY50,money50);
                SpzUtils.putInt(Constant.MONEY20,money20);
                SpzUtils.putInt(Constant.MONEY10,money10);
                SpzUtils.putInt(Constant.MONEY5,money5);
                SpzUtils.putInt(Constant.MONEY1,money1);
                //其他存款
                SpzUtils.putInt(Constant.COIN2,coin2);
                SpzUtils.putInt(Constant.CHEQUE2,cheque2);
                SpzUtils.putInt(Constant.PAPER_MONEY2,paper_money2);
                SpzUtils.putInt(Constant.OTHER2,other2);
                coin=0;
                cheque=0;
                paper_money=0;
                other=0;
            }
        }
    }


    //存款报告打印 墨西哥币
    public static void deposit_Print_SampleTicket_MXN(Activity activity, List<DepositDetailsActivity.DepositDetailBean> list, Pointer h){
        activity2=activity;
        int total=0;
        int num=0;
        int num1000 = SpzUtils.getInt(Constant.NUM_MXN1000, 0);
        int num500 = SpzUtils.getInt(Constant.NUM_MXN500, 0);
        int num200 = SpzUtils.getInt(Constant.NUM_MXN200, 0);
        int num100 = SpzUtils.getInt(Constant.NUM_MXN100, 0);
        int num50 = SpzUtils.getInt(Constant.NUM_MXN50, 0);
        int num20 = SpzUtils.getInt(Constant.NUM_MXN20, 0);


        int money1000 = SpzUtils.getInt(Constant.MONEY_MXN1000, 0);
        int money500 = SpzUtils.getInt(Constant.MONEY_MXN500, 0);
        int money200 = SpzUtils.getInt(Constant.MONEY_MXN200, 0);
        int money100 = SpzUtils.getInt(Constant.MONEY_MXN100, 0);
        int money50 = SpzUtils.getInt(Constant.MONEY_MXN50, 0);
        int money20 = SpzUtils.getInt(Constant.MONEY_MXN20, 0);

        int save_num1000=0,save_num500=0,save_num200=0, save_num100=0,save_num50=0,save_num20=0;
        int save_money1000=0,save_money500=0,save_money200=0, save_money100=0,save_money50=0,save_money20=0;

        int coin2 = SpzUtils.getInt(Constant.COIN2_MXN, 0);
        int cheque2 = SpzUtils.getInt(Constant.CHEQUE2_MXN, 0);
        int paper_money2 = SpzUtils.getInt(Constant.PAPER_MONEY2_MXN, 0);
        int other2 = SpzUtils.getInt(Constant.OTHER2_MXN, 0);
        //将其他存款数累加
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
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.deposit_report)+" ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.client_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.CLIENT)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.site_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.SITE)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.deposit_category_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.machine_No_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.SN)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.user_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.USER)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.currency_print)+Constant.MXN+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.banknotes_print)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.face_value)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.piece)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            if (list!=null){
                for (int i = 0; i <list.size() ; i++) {
                    DepositDetailsActivity.DepositDetailBean depositDetailBean = list.get(i);
                    if (depositDetailBean.deposit==1000){
                        save_money1000+=depositDetailBean.sum;
                        save_num1000+=depositDetailBean.count;
                        total+=depositDetailBean.sum;
                        num+=depositDetailBean.count;
                        num1000+=depositDetailBean.count;
                        money1000+=depositDetailBean.sum;

                    }else if (depositDetailBean.deposit==500){
                        save_money500+=depositDetailBean.sum;
                        save_num500+=depositDetailBean.count;
                        total+=depositDetailBean.sum;
                        num+=depositDetailBean.count;
                        num500+=depositDetailBean.count;
                        money500+=depositDetailBean.sum;

                    }else if (depositDetailBean.deposit==200){
                        save_money200+=depositDetailBean.sum;
                        save_num200+=depositDetailBean.count;
                        total+=depositDetailBean.sum;
                        num+=depositDetailBean.count;
                        num200+=depositDetailBean.count;
                        money200+=depositDetailBean.sum;
                    } else if (depositDetailBean.deposit==100){
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
                    }
                }
            }

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("1000"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_num1000+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_money1000+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("500"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_num500+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_money500+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("200"));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_num200+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(save_money200+"\r\n"));

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


            {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.amount_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(num+""));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 300);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(total+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetTextUnderline(h,500);
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other_print)+"\r\n"));
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+coin+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+cheque+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+paper_money+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+other+"\r\n"));

            }

            {
                total=total+coin+cheque+paper_money+other;
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.STC+" "+content(R.string.total_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(total+""));
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.finish_print)+" ***"));
                AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            }

            {
                SpzUtils.putInt(Constant.NUM_MXN1000,num1000);
                SpzUtils.putInt(Constant.NUM_MXN500,num500);
                SpzUtils.putInt(Constant.NUM_MXN200,num200);
                SpzUtils.putInt(Constant.NUM_MXN100,num100);
                SpzUtils.putInt(Constant.NUM_MXN50,num50);
                SpzUtils.putInt(Constant.NUM_MXN20,num20);

                SpzUtils.putInt(Constant.MONEY_MXN100,money1000);
                SpzUtils.putInt(Constant.MONEY_MXN50,money500);
                SpzUtils.putInt(Constant.MONEY_MXN20,money200);
                SpzUtils.putInt(Constant.MONEY_MXN100,money100);
                SpzUtils.putInt(Constant.MONEY_MXN50,money50);
                SpzUtils.putInt(Constant.MONEY_MXN20,money20);

                //其他存款
                SpzUtils.putInt(Constant.COIN2_MXN,coin2);
                SpzUtils.putInt(Constant.CHEQUE2_MXN,cheque2);
                SpzUtils.putInt(Constant.PAPER_MONEY2_MXN,paper_money2);
                SpzUtils.putInt(Constant.OTHER2_MXN,other2);
                coin=0;
                cheque=0;
                paper_money=0;
                other=0;
            }
        }
    }

    private static int coin,cheque,paper_money,other,how_much,n2;
    //选择存款报告打印
    public static void select_deposit_Print_SampleTicket(Activity activity, int n, Pointer h){
        activity2=activity;
        n2=n;
        //获取输入金额
        how_much = SpzUtils.getInt(Constant.HOW_MUCH, -1);
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
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.other_deposit_print)+" ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.client_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("client")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.site_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("site")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.deposit_category_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)+"\r\n"));
            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)+"\r\n"));
            } else if (n == 2) {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));
            }else {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)+"\r\n"));
            }

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.machine_No_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.SN)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.user_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("user")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));
        }
        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other_print)+"\r\n"));
        }
        {
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+how_much+"\r\n"));
                coin+=how_much;
            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+how_much+"\r\n"));
                cheque+=how_much;
            }else if (n==2){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+how_much+"\r\n"));
                paper_money+=how_much;
            }else if (n==3){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+how_much+"\r\n"));
                other+=how_much;
            }
        }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.STC+" "+content(R.string.total_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(how_much+""));
            }

            {
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.finish_print)+" ***"));
                AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            }
        }

    //选择存款报告打印 墨西哥币
    public static void select_deposit_Print_SampleTicket_MXN(Activity activity, int n, Pointer h){
        activity2=activity;
        n2=n;
        //获取输入金额
        how_much = SpzUtils.getInt(Constant.HOW_MUCH, -1);
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
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.other_deposit_print)+" ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.client_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("client")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.site_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("site")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.deposit_category_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)+"\r\n"));
            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)+"\r\n"));
            } else if (n == 2) {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));
            }else {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)+"\r\n"));
            }

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.machine_No_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.SN)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.user_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("user")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));
        }
        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other_print)+"\r\n"));
        }
        {
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+how_much+"\r\n"));
                coin+=how_much;
            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+how_much+"\r\n"));
                cheque+=how_much;
            }else if (n==2){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+how_much+"\r\n"));
                paper_money+=how_much;
            }else if (n==3){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.MXN+" "+how_much+"\r\n"));
                other+=how_much;
            }
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.STC+" "+content(R.string.total_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(how_much+""));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.finish_print)+" ***"));
            AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
        }
    }


    //记录存款报告打印
    public static void record_deposit_Print_SampleTicket(Activity activity,int n,int money, Pointer h) {
            activity2=activity;
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
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.other_deposit_print)+" ***\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_Left);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.client_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("client")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.site_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("site")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.deposit_category_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)+"\r\n"));
            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)+"\r\n"));
            } else if (n == 2) {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));
            }else {
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)+"\r\n"));
            }

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.machine_No_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString(Constant.SN)+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.user_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(SpzUtils.getString("user")+"\r\n"));

            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTimeDay()+""));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(TimeFormartUtils.getTime()+"\r\n"));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other_print)+"\r\n"));
        }

        {
            if (n==0){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.coin)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+money+"\r\n"));

            }else if (n==1){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.check)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+money+"\r\n"));

            }else if (n==2){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.bill)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+money+"\r\n"));

            }else if (n==3){
                AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
                AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 0);
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.kind_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.other)+"\r\n"));

                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 22);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(content(R.string.sum_print)));
                AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 150);
                AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.CNY +" "+money+"\r\n"));

            }

        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetTextBold(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(Constant.STC+" "+content(R.string.total_print)));
            AutoReplyPrint.INSTANCE.CP_Pos_SetHorizontalAbsolutePrintPosition(h, 240);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString(money+""));
        }

        {
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_SetAlignment(h, AutoReplyPrint.CP_Pos_Alignment_HCenter);
            AutoReplyPrint.INSTANCE.CP_Pos_PrintTextInUTF8(h, new WString("*** "+content(R.string.finish_print)+" ***"));
            AutoReplyPrint.INSTANCE.CP_Pos_FeedAndHalfCutPaper(h);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
            AutoReplyPrint.INSTANCE.CP_Pos_FeedLine(h, 1);
        }

        }

    private static String content(int site_print) {
        String string = activity2.getResources().getString(site_print);
        return string;
    }

    public static void clear_thisOther(){
        switch (n2){
            case 0:
                coin-=how_much;
                break;
            case 1:
                cheque-=how_much;
                break;
            case 2:
                paper_money-=how_much;
                break;
            case 3:
                other-=how_much;
                break;
        }
    }
}