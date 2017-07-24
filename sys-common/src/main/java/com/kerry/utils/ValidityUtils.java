package com.kerry.utils;

/**
 * Created by wangshen on 2017/7/24.
 */
public class ValidityUtils {

    /**
     * 用于字符串合法校验
     *
     * @param params
     * @return false 不合法 、true 合法
     */
    public static boolean validityStr(String[] params) {
        if (params == null || params.length == 0)
            return false;
        for (String param : params) {
            if (param == null || param.equals("") || "null".equals(param) || param.trim().length() == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * 用于字符串合法校验
     *
     * @param param
     * @return false 不合法 、true 合法
     */
    public static boolean validityStr(String param) {
        if (param == null || param.equals("") || param.trim().length() == 0 || "null".equalsIgnoreCase(param))
            return false;
        return true;
    }
}
