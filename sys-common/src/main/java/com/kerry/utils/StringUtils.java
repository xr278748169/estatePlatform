package com.kerry.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * 过滤特殊字符
     * @param chars
     * @return
     */
    public static String filterSpecialChar(String chars){
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(chars);
        return m.replaceAll("").trim();
    }

}
