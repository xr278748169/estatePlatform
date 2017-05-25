package com.kerry.utils;

/**
 * Created by wangshen on 2017/5/23.
 */
public class StringUtils {

    /**
     * 判断字符串是否为null或空字符串
     * @param val
     * @return
     */
    public static boolean isEmpty(String val){
        if(val == null){
            return false;
        }
        if(val.trim().equals("")){
            return false;
        }
        return true;
    }
}
