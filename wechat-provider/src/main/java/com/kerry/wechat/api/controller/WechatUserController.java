package com.kerry.wechat.api.controller;

import com.kerry.wechat.api.inter.IWechatUserInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信用户操作
 * Created by wangshen on 2017/7/24.
 */
@RestController
@RequestMapping("/wechat/user")
public class WechatUserController {

    @Autowired
    private IWechatUserInter wechatUserInter;

    /**
     * 用户授权处理
     * @param code
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/{accountId}/{code}/oauth", method = RequestMethod.GET)
    public String oauthUser(@PathVariable("code") String code, @PathVariable("accountId") String accountId) throws Exception {
        return wechatUserInter.oauthUser(code, accountId);
    }
}
