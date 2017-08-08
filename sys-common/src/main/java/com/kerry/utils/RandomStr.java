package com.kerry.utils;

import java.util.UUID;

/**
 * Created by admin on 2017/2/4.
 */
public class RandomStr {

    private final static String BASE_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getRandomStr(int length) {
        StringBuffer sb = new StringBuffer();
        int len = BASE_STR.length();
        for (int i = 0; i < length; i++) {
            sb.append(BASE_STR.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }


    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static int getRandNum(int min, int max) {
        int randNum = min + (int) (Math.random() * ((max - min) + 1));
        return randNum;
    }

    /**
     * 生成6位数短信验证码
     * @return
     */
    public static String getSmsCode(){
        int min = 0;
        int max = 999999;
        int randNum = min + (int) (Math.random() * ((max - min) + 1));
        String result =  randNum+"";
        if(result.length()!=6){
            int len = 6-result.length();
            while (len>=0) {
                result = result+"0";
                len--;
            }
        }
        return result;
    }

}
