package com.kerry.estate.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 验证码服务
 * Created by wangshen on 2017/8/2.
 */
@FeignClient(name = "sys-auth")
public interface CaptchaClient {

    @RequestMapping(value = "/sys/captcha/sms/{telephone}", method = RequestMethod.GET)
    String generateSmsCode(@PathVariable("telephone") String telephone);

    @RequestMapping(value = "/sys/captcha/verify/sms/{telephone}/{authCode}", method = RequestMethod.GET)
    String verifySmsCode(@PathVariable("telephone") String telephone, @PathVariable("authCode") String authCode);
}
