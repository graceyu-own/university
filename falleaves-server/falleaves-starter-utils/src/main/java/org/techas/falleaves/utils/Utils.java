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

}