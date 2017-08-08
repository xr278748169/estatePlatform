package com.kerry.estate.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信用户
 * Created by wangshen on 2017/8/2.
 */
@FeignClient(name = "wechat-provider")
public interface TUserClient {

    @RequestMapping(value = "/tuser/update/{tuId}/{state}", method = RequestMethod.GET)
    String updateBussState(@PathVariable("tuId") String tuId, @PathVariable("state") String state);
}
