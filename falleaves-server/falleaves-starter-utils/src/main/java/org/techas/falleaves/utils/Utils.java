package org.techas.falleaves.utils;

import java.util.Date;
import java.util.Random;

public class Utils {

    public static String getRandomString(int length){

        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random=new Random();

        StringBuffer sb=new StringBuffer();

        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    public static long getCurrentTimestamp() {
        return new Date().getTime();
    }

    /**
     * 字符串转Integer型整数
     * @param val   字符串
     * @return  Integer整数
     */
    public static Integer toInteger(String val) {
        try {
            return Integer.valueOf(val);
        } catch (NumberFormatException ignore) {
            return null;
        }
    }

    /**
     * 字符串转Long型
     * @param val   字符串
     * @return  long整数
     */
    public static Long toLong(String val) {
        try {
            return Long.valueOf(val);
        } catch (NumberFormatException ignore) {
            return null;
        }
    }

    /**
     * 获取数组URL的参数
     * @param array 数组
     * @param key   键
     *
     * @return value
     */
    public static String getArrayUrlParam(String[] array, String key) {
        if("".equals(key)) return null;
        for(String arr : array) {
            if("".equals(arr)) return null;
            String[] kv;
            if((kv = arr.split("=")).length != 2) return null;
            if(key.equals(kv[0])) {
                return kv[1];
            }
        }
        return null;
    }

    /**
     * 获取URL参数
     * @param text urlKV
     * @return  url值
     */
    public static String getUrlParam(String text) {
        if("".equals(text)) return null;
        String[] kv;
        if((kv = text.split("=")).length != 2) return null;
        return kv[1];
    }
}