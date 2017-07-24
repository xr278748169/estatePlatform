package com.kerry.wechat.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信用户
 * Created by wangshen on 2017/7/24.
 */
@FeignClient(name = "wechat-provider")
public interface WechatUserClient {

    @RequestMapping(value = "/wechat/user/{accountId}/{code}/oauth", method = RequestMethod.GET)
    String oauthUser(@PathVariable("code") String code, @PathVariable("accountId") String accountId);
}
