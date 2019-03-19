package com.center.demoapplication.utils;


import java.util.Formatter;
import java.util.Locale;

/**
 * Created by aaa on 2019/3/19.
 */

public class Utils {

    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;

    public Utils() {
        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
    }

    /*
    * 把毫秒转换成1：20：30
    * */
    public String stringForTime(int timeMs){

        int totalSends = timeMs / 100;
        int seconds = totalSends / 60;
        int minutes = (totalSends / 60) % 60;
        int hours = totalSends / 3600;
        mFormatBuilder.setLength(0);
        if (hours>0){
            return mFormatter.format("%d:%02d:%02d",hours,minutes,seconds).toString();
        }else {
            return mFormatter.format("%02d:%02d",minutes,seconds).toString();
        }
    }
}
