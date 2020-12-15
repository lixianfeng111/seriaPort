package com.licheedev.serialtool.util;


import java.text.SimpleDateFormat;

/**
 * function ：时间戳转换工具类
 * author ：Mr.ZHU
 * date ：2019/1/22
 */
public class TimeFormartUtils {

    private static long currentTime;

    public static String getTimeDay() {
        currentTime = System.currentTimeMillis();
        String timeDay = new SimpleDateFormat("yyyy-MM-dd").format(currentTime);
        return timeDay;
    }
    //一定要先调用上面的方法不然currentTime为空
    public static String getTime() {
        String timeNow = new SimpleDateFormat("HH:mm:ss").format(currentTime);
        return timeNow;
    }
}