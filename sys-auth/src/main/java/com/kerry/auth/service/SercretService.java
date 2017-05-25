package com.kerry.auth.service;

/**
 * Created by wangshen on 2017/5/24.
 */
public interface SercretService {

    /**
     * 生成前端UI连接校验信息
     * @param uiSign
     * @return
     */
    String generater(String uiSign);
}
