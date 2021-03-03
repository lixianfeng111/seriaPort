package com.licheedev.serialtool.net;

import android.content.Context;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtil {

    private static OkHttpClient sOkHttpClient;

    public static void init(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(3000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(3000, TimeUnit.MILLISECONDS);
        builder.connectTimeout(3000, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (message != null) {
                    if (message.startsWith("{") || message.startsWith("[")) {
                        LogUtil.json(message);
                    } else {
                        LogUtil.d(message);
                    }
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY));
        builder.addInterceptor(new OkLogIntorceptor());
        builder.addInterceptor(new OkHeaderIntorceptor(context));
        sOkHttpClient = builder.build();
    }
    public static OkHttpClient getInstance()  {
        if (sOkHttpClient == null) {
            new OkHttpUtil();
        }
        return sOkHttpClient;
    }
}
