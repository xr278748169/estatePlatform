package com.kerry.auth.controller;

import com.kerry.auth.service.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码接口
 * Created by wangshen on 2017/8/1.
 */
@RestController
@RequestMapping("/sys/captcha")
public class CaptchaController {

    @Autowired
    private ICaptchaService captchaService;

    /**
     * 获取短信验证码
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/sms/{telephone}", method = RequestMethod.GET)
    public String generateSmsCode(@PathVariable("telephone") String telephone) {
        return captchaService.generateSmsCode(telephone);
    }

    /**
     * 校验短信验证码
     * @param telephone
     * @param authCode
     * @return
     */
    @RequestMapping(value = "/verify/sms/{telephone}/{authCode}", method = RequestMethod.GET)
    public String verifySmsCode(@PathVariable("telephone") String telephone, @PathVariable("authCode") String authCode){
        return captchaService.verifySmsCode(telephone, authCode);
    }
}
