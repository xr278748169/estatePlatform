package com.kerry.auth.service;

/**
 * 验证码
 * Created by wangshen on 2017/8/1.
 */
public interface ICaptchaService {

    /**
     * 生成短信验证码
     * @param telephone
     * @return
     */
    String generateSmsCode(String telephone);

    /**
     * 短信验证码校验
     * @param telephone
     * @param authCode
     * @return
     */
    String verifySmsCode(String telephone, String authCode);

}
