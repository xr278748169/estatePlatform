package com.kerry.wechat.api.client;

import org.dom4j.Element;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信事件
 * Created by wangshen on 2017/6/26.
 */
@FeignClient(name = "wechat-provider")
public interface EventClient {

    @RequestMapping(value = "/wechat/event/{accountId}/focus", method = RequestMethod.POST)
    String focus(@RequestBody Element element,@PathVariable("accountId") String accountId);
}
