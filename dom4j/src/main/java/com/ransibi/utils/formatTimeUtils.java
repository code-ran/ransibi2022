package com.ransibi.utils;

/**
 * @description:
 * @author: rsb
 * @description: 2022-08-25-17-12
 * @description:
 * @Version: 1.0.0
 */
public class formatTimeUtils {
    public static String formatTime(long ms) {

        int ss = 1;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        } else {
            sb.append(0 + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        } else {
            sb.append("0" + "小时");
        }
        // if(minute > 0) {
        // sb.append(minute+"分钟");
        // } else{
        // sb.append("00"+"分钟");
        // }
        // if(second > 0) {
        // sb.append(second+"秒");
        // } else{
        // sb.append("00"+"秒");
        // }

        return sb.toString();
    }
}
