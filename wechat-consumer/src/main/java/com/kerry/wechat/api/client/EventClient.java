package com.kerry.wechat.api.client;

import com.kerry.wechat.model.AccountModel;
import org.dom4j.Element;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信事件
 * Created by wangshen on 2017/6/26.
 */
@FeignClient(name = "wechat-provider")
public interface EventClient {

    @RequestMapping(value = "/wechat/event/focus", method = RequestMethod.POST)
    String focus(@RequestBody Element element, @RequestBody AccountModel accountModel);
}
