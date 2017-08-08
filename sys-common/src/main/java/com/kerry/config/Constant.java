package com.kerry.config;

/**
 * Created by wangshen on 2017/4/6.
 */
public class Constant {

    /**
     * 数据操作成功
     */
    public final static String DATA_RESULT_SUCCESS = "操作成功";

    /**
     * 数据操作失败
     */
    public final static String DATA_RESULT_ERROR = "操作失败";

    /**
     * 数据重复
     */
    public final static String DTAT_RESULT_DUP = "数据重复";

    /**
     * 存在下级数据
     */
    public final static String DTAT_RESULT_SUB = "存在下级数据";

    /**
     * 系统默认密码123456
     */
    public final static String DEFAULT_PASSWORD = "123456";
    /**
     * 系统登录缓存key
     */
    public final static String LOGIN_TOKEN_INFO = "LOGIN_TOKEN_INFO";
    /**
     * 地区代码缓存key
     */
    public final static String AREA_CODE_CACHE = "AREA_CODE_CACHE";
    /**
     * 数据字典缓存key
     */
    public final static String DICT_CODE_CACHE = "DICT_CODE_CACHE";

    /**
     * 短信验证码有效时间10分钟
     */
    public final static long SMS_CODE_INVALID_TIME = 10*60L;
}
