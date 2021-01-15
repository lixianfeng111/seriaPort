package com.licheedev.serialtool.util;

import com.licheedev.serialtool.util.constant.Constant;

public class GetCurrencyUtil {
    public static String getCurrency(){
      String print_currency = SpzUtils.getString(Constant.PRINT_CURRENCY);
      return print_currency;
    }
}
