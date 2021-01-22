package com.licheedev.serialtool.util;

import android.app.Activity;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.message.LogManager;

import static com.licheedev.serialtool.comn.message.LogManager.SAVE_SUCCESS_COMMAND;

public class SystemErrorsUtil {

    private static String hexstr1 = null;

    private static Activity activity;

    public SystemErrorsUtil(Activity activity) {
        this.activity = activity;
    }

    public static void getError15(byte[] received,String hexStr) {
        hexstr1 = null;
            if (((char) (received[7] & 0xff) & 0x01) == 0x01) {
                hexstr1 = "PS01 "+content(R.string.left_errors);
            } else if (((char) (received[7] & 0xff) & 0x02) == 0x02) {
                hexstr1 = "PS01 "+content(R.string.right_errors);
            } else if (((char) (received[7] & 0xff) & 0x04) == 0x04) {
                hexstr1 = "PS02 "+content(R.string.left_errors);
            } else if (((char) (received[7] & 0xff) & 0x08) == 0x08) {
                hexstr1 = "PS02 "+content(R.string.right_errors);
            } else if (((char) (received[7] & 0xff) & 0x10) == 0x10) {
                hexstr1 = "PS03 "+content(R.string.left1_errors);
            } else if (((char) (received[7] & 0xff) & 0x20) == 0x20) {
                hexstr1 = "PS03 "+content(R.string.left2_errors);
            } else if (((char) (received[7] & 0xff) & 0x40) == 0x40) {
                hexstr1 = "PS03 "+content(R.string.right1_errors);
            } else if (((char) (received[7] & 0xff) & 0x80) == 0x80) {
                hexstr1 = "PS03 "+content(R.string.right2_errors);
            }
            if (((char) (received[8] & 0xff) & 0x01) == 0x01) {
                hexstr1 = "PS04 "+content(R.string.errors);
            } else if (((char) (received[8] & 0xff) & 0x02) == 0x02) {
                hexstr1 = "PS06 "+content(R.string.errors);
            } else if (((char) (received[8] & 0xff) & 0x04) == 0x04) {
                hexstr1 = content(R.string.stacker_error);
            } else if (((char) (received[8] & 0xff) & 0x08) == 0x08) {
                hexstr1 = content(R.string.pocket_open_error);
            } else if (((char) (received[8] & 0xff) & 0x10) == 0x10) {
                hexstr1 = "PS08 "+content(R.string.left_errors);
            } else if (((char) (received[8] & 0xff) & 0x20) == 0x20) {
                hexstr1 = "PS08 "+content(R.string.right_errors);
            }
            if (((char) (received[9] & 0xff) & 0x01) == 0x01) {
                hexstr1 = content(R.string.errors)+" 1";//错误未定义，暂时标记错误1
            } else if (((char) (received[9] & 0xff) & 0x02) == 0x02) {
                hexstr1 = content(R.string.cover_door_open_error);
            } else if (((char) (received[9] & 0xff) & 0x04) == 0x04) {
                hexstr1 = content(R.string.cover_door_close_error);
            } else if (((char) (received[9] & 0xff) & 0x08) == 0x08) {
                hexstr1 = content(R.string.cover_door_open_error);
            } else if (((char) (received[9] & 0xff) & 0x10) == 0x10) {
                hexstr1 = content(R.string.errors)+" 2";//错误未定义，暂时标记错误2

            } else if (((char) (received[9] & 0xff) & 0x20) == 0x20) {
                hexstr1 = content(R.string.errors)+" 3";//错误未定义，暂时标记错误3
            } else if (((char) (received[9] & 0xff) & 0x40) == 0x40) {
                hexstr1 = "PS09 "+content(R.string.left_errors);
            } else if (((char) (received[9] & 0xff) & 0x80) == 0x80) {
                hexstr1 = "PS09 "+content(R.string.right_errors);
            }
            if (((char) (received[10] & 0xff) & 0x01) == 0x01) {
                hexstr1 = content(R.string.paper_on_hopper);
            } else if (((char) (received[10] & 0xff) & 0x02) == 0x02) {
                hexstr1 = "PS05 "+content(R.string.middle_errors);
            } else if (((char) (received[10] & 0xff) & 0x04) == 0x04) {
                hexstr1 = "PS05 "+content(R.string.left_errors);
            } else if (((char) (received[10] & 0xff) & 0x08) == 0x08) {
                hexstr1 = "PS05 "+content(R.string.right_errors);
            } else if (((char) (received[10] & 0xff) & 0x0E) == 0x0E) {
                hexstr1 = "PS05 "+content(R.string.left_middle_right_error);
            }
            if (hexstr1!=null){
                LogManager.instance().postError(hexstr1);
            }

            if (hexstr1.isEmpty()){
                hexstr1=hexStr;
            }
        LogPlus.e("read_thread2","传感器 " + hexStr);
        LogPlus.e("read_thread2","传感器 " + hexstr1);
    }
    public static void getError44(byte[] received,String hexStr){
        hexstr1 = null;
        if(((char)(received[7]&0xff)&0x01)==0x00) {
            hexstr1= content(R.string.bag_not_in_position);
            LogManager.instance().postError(hexstr1);
        } else if(((char)(received[7]&0xff)&0x02)==0x00) {
            hexstr1= content(R.string.door_not_close);
            LogManager.instance().postError(hexstr1);
        } else if(((char)(received[7]&0xff)&0x04)==0x00) {
            hexstr1= content(R.string.alertor_alarms);
            LogManager.instance().postError(hexstr1);
        } else if(((char)(received[8]&0xff)&0x01)==0x01) {
            hexstr1= content(R.string.cover_door_sensor_error);
            LogManager.instance().postError(hexstr1);
        }
        else if(((char)(received[8]&0xff)&0x02)==0x02) {
            hexstr1= content(R.string.gate_door_sensor_error);
            LogManager.instance().postError(hexstr1);
        }
        if (hexstr1!=null){
            LogManager.instance().postError(hexstr1);
        }
        if(((char)(received[7]&0x08))==0x08) {
            hexstr1="传感器检测到钞票";
            SerialPortManager.instance().sendCountCommand();
        }else if (hexstr1==null){
            hexstr1=hexStr;
        }
        LogPlus.e("read_thread2","传感器 " + hexStr);
        LogPlus.e("read_thread2","传感器 " + hexstr1);
    }

    public static void getError22(byte[] received){
        hexstr1 = null;
        int result = received[8];
        switch (result){
            case 6:
                hexstr1 = "";
                break;
            case 0x15:
                hexstr1 = content(R.string.cannot_start_no_work);
                break;
            case 0x16:
                hexstr1 = content(R.string.cannot_start_no_paper);
                break;
            case 0x18:
                hexstr1 = content(R.string.cannot_start_paper_limit);
                break;
            case 0x19:
                hexstr1 = content(R.string.cannot_start_value_limit);
                break;
            case 0x1A:
                hexstr1 = content(R.string.cannot_start_other_reason);
                break;
        }
        if (!hexstr1.isEmpty()){
            LogManager.instance().postError(hexstr1);
        }

        LogPlus.e("read_thread","开始点算 "+hexstr1);
    }

    public static void getError25(byte[] received,String hexStr){
        hexstr1 = null;
        byte status = received[7];
        LogPlus.e("read_thread","存储收钞口纸币 " + hexStr);
        switch (status){
            case 6:
                LogPlus.e("read_thread","纸币存储成功 ");
                break;
            case 16:
                hexstr1 = content(R.string.banknote_deposit_fail);
                break;
            case 17:
                hexstr1 = content(R.string.banknote_on_8);
                break;
            case 18:
                hexstr1 = content(R.string.deposit_timeout);
                break;
        }
        if (hexstr1!=null){
            LogManager.instance().postError(hexstr1);
        }

        LogManager.instance().post(new LogManager.ReceiveData(received, SAVE_SUCCESS_COMMAND));
        LogPlus.e("read_thread",hexStr);
        LogPlus.e("read_thread",hexstr1);
    }

    public static void getError46(byte[] received){
        hexstr1 = null;
        byte status = received[7];
        switch (status){
            case 1:
                hexstr1 = content(R.string.no_sd_card);
                break;
            case 2:
                hexstr1 = content(R.string.sd_initialization_failed);
                break;
            case 3:
                LogPlus.e("read_thread","sd卡状态 初始化成功");
                break;
        }
        if (hexstr1!=null){
            LogManager.instance().postError(hexstr1);
        }
        SerialPortManager.instance().sendSDcardAck();
        LogPlus.e("read_thread",hexstr1);
    }


    private static String content(int system_error) {
        String string = activity.getResources().getString(system_error);
        return string;
    }
}