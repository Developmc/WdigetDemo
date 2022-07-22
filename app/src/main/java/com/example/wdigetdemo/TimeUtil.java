package com.example.wdigetdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author: clement
 * Create: 2022/7/22
 * Desc:
 */
public class TimeUtil {

    public final static String HOUR_MM_SS = "HH:mm:ss";

    public static String long2String(long time, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
            return simpleDateFormat.format(new Date(time));
        } catch (Exception e) {
            return "";
        }

    }

}
