package com.kerry.wechat.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信用户cache
 * Created by wangshen on 2017/7/24.
 */
@FeignClient(name = "wechat-provider")
public interface WechatUserCacheClient {

    @RequestMapping(value = "/wechat/user/cache/{accountId}/{openId}/set", method = RequestMethod.GET)
    String setUserCache(@PathVariable("openId") String openId, @PathVariable("accountId") String accountId);

    @RequestMapping(value = "/wechat/user/cache/{token}/get", method = RequestMethod.GET)
    String getUserCache(@PathVariable("token") String token);
}
