package com.kerry.estate.owner.web;

import com.kerry.estate.ValidFactory;
import com.kerry.estate.client.CaptchaClient;
import com.kerry.estate.dto.AuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 业主接口
 * Created by wangshen on 2017/8/2.
 */
@RestController
@RequestMapping("/api/estate/open/owner")
public class OwnAuthRestful {

    @Autowired
    private CaptchaClient captchaClient;

    /**
     * 获取业主认证短信验证码
     * @param telephone
     * @return
     */
    @RequestMapping(value = "/sms/{telephone}", method = RequestMethod.GET)
    @ResponseBody
    public String smsCode(@PathVariable("telephone") String telephone){
        return captchaClient.generateSmsCode(telephone);
    }

    /**
     * 业主信息认证
     * @param authDto
     * @param result
     * @return
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public String ownAuth(@Valid AuthDto authDto, BindingResult result){
        if (result.hasErrors()) {
            return ValidFactory.message(result);
        }
        return "";
    }
}
