package com.kerry.member.client;

import com.kerry.model.ClientUser;
import com.kerry.model.ValidateClient;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 客户端操作
 * Created by wangshen on 2017/5/25.
 */
@FeignClient(name = "sys-auth")
public interface SercretClient {

    @RequestMapping(value = "/sys/auth/{clientType}/conn/{uiSign}", method = RequestMethod.GET)
    String generater(@PathVariable("clientType") String clientType,@PathVariable("uiSign") String uiSign);

    @RequestMapping(value = "/sys/auth/clear/{accessToken}", method = RequestMethod.GET)
    String clear(@PathVariable("accessToken") String accessToken);

    @RequestMapping(value = "/sys/auth/get/{accessToken}", method = RequestMethod.GET)
    ClientUser getClientUser(@PathVariable("accessToken") String accessToken);

    @RequestMapping(value = "/sys/auth/update", method = RequestMethod.POST)
    String updateClientUser(@RequestBody ClientUser clientUser);

    @RequestMapping(value = "/sys/auth/validate/url", method = RequestMethod.POST)
    String validateUrl(@RequestBody ValidateClient validateClient);
}
