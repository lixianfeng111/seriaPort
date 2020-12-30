package com.licheedev.serialtool.util;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.sun.jna.Pointer;

public class OpenPortUtil {

    private static Pointer h=Pointer.NULL;

    private static Pointer port(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //波特率为9600
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom("/dev/ttyS3", 9600, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, AutoReplyPrint.CP_ComFlowControl_XonXoff, 0);
            }
        }).start();
        return h;
    }

    public static Pointer getPort(){
        if (h==null){
            h=port();
        }
        return h;
    }
}