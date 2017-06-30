package com.kerry.wechat.api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信事件
 * Created by wangshen on 2017/6/26.
 */
@FeignClient(name = "wechat-provider")
public interface EventClient {


    @RequestMapping(value = "/wechat/event/{accountId}/{openId}/focus", method = RequestMethod.GET)
    String focus(@PathVariable("openId") String openId, @PathVariable("accountId") String accountId);
}
