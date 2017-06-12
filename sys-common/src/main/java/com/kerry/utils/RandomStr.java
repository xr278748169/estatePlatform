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

    public static void main(String[] args) {
        String s = getRandomStr(128);
        String token = SecurityUtil.AESEncode(s,"VAGgPTIAsnZFJSvtjSIyMsFsDaUvAjzv");
        System.out.println(token);
    }
}
