package com.licheedev.serialtool.util;

import android.app.Activity;

import com.licheedev.serialtool.App;
import com.licheedev.serialtool.R;

public class SystemErrorsUtil {
    private static String hexstr1 = null;
    private static Activity activity;

    public SystemErrorsUtil(Activity activity) {
        this.activity = activity;
    }

    public static String getError(byte[] received) {
            if (((char) (received[7] & 0xff) & 0x01) == 0x01) {
                hexstr1 = "PS01 "+content(R.string.left_errors);
            } else if (((char) (received[7] & 0xff) & 0x02) == 0x02) {
                hexstr1 = "PS01"+content(R.string.right_errors);
            } else if (((char) (received[7] & 0xff) & 0x04) == 0x04) {
                hexstr1 = "PS02左错误";
            } else if (((char) (received[7] & 0xff) & 0x08) == 0x08) {
                hexstr1 = "PS02右错误";
            } else if (((char) (received[7] & 0xff) & 0x10) == 0x10) {
                hexstr1 = "PS03左1错误";
            } else if (((char) (received[7] & 0xff) & 0x20) == 0x20) {
                hexstr1 = "PS03左2错误";
            } else if (((char) (received[7] & 0xff) & 0x40) == 0x40) {
                hexstr1 = "PS03右1错误";
            } else if (((char) (received[7] & 0xff) & 0x80) == 0x80) {
                hexstr1 = "PS03右2错误";
            }
            if (((char) (received[8] & 0xff) & 0x01) == 0x01) {
                hexstr1 = "PS04错误";
            } else if (((char) (received[8] & 0xff) & 0x02) == 0x02) {
                hexstr1 = "PS06错误";
            } else if (((char) (received[8] & 0xff) & 0x04) == 0x04) {
                hexstr1 = "收钞电机错误";
            } else if (((char) (received[8] & 0xff) & 0x08) == 0x08) {
                hexstr1 = "存款口打开错误";
            } else if (((char) (received[8] & 0xff) & 0x10) == 0x10) {
                hexstr1 = "PS08左错误";
            } else if (((char) (received[8] & 0xff) & 0x20) == 0x20) {
                hexstr1 = "PS08右错误";
            }
            if (((char) (received[9] & 0xff) & 0x01) == 0x01) {
                hexstr1 = "";
            } else if (((char) (received[9] & 0xff) & 0x02) == 0x02) {
                hexstr1 = " 罩门打开出错";
            } else if (((char) (received[9] & 0xff) & 0x04) == 0x04) {
                hexstr1 = "罩门关闭出错";
            } else if (((char) (received[9] & 0xff) & 0x08) == 0x08) {
                hexstr1 = "落钞门关闭出错";
            } else if (((char) (received[9] & 0xff) & 0x10) == 0x10) {
                hexstr1 = "";
            } else if (((char) (received[9] & 0xff) & 0x20) == 0x20) {
                hexstr1 = "";
            } else if (((char) (received[9] & 0xff) & 0x40) == 0x40) {
                hexstr1 = "PS09左错误";
            } else if (((char) (received[9] & 0xff) & 0x80) == 0x80) {
                hexstr1 = "PS09右错误";
            }
            if (((char) (received[10] & 0xff) & 0x01) == 0x01) {
                hexstr1 = "置钞口有纸币";
            } else if (((char) (received[10] & 0xff) & 0x02) == 0x02) {
                hexstr1 = "PS05中错误";
            } else if (((char) (received[10] & 0xff) & 0x04) == 0x04) {
                hexstr1 = "PS05左错误";
            } else if (((char) (received[10] & 0xff) & 0x08) == 0x08) {
                hexstr1 = "PS05右错误";
            } else if (((char) (received[10] & 0xff) & 0x0E) == 0x0E) {
                hexstr1 = "PS05左中右错误";
            }
            return hexstr1;
    }
    private static String content(int system_error) {
        String string = activity.getResources().getString(system_error);
        return string;
    }
}