package com.kerry.member.controler;

import com.kerry.member.client.SercretClient;
import com.kerry.model.ClientUser;
import com.kerry.model.ValidateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 系统应用管理
 * Created by wangshen on 2017/5/25.
 */
@RestController
@RequestMapping("/api/sys/app")
public class AppController {

    @Autowired
    private SercretClient sercretClient;

    /**
     * 生成前端UI连接校验信息
     * @param clientType
     * @param uiSign
     * @return
     */
    @RequestMapping(value = "/{clientType}/conn/{uiSign}", method = RequestMethod.GET)
    public String generater(@PathVariable("clientType") String clientType, @PathVariable("uiSign") String uiSign){
        return sercretClient.generater(clientType,uiSign);
    }

    /**
     * 用户退出或者刷新系统后，清空服务端用户信息
     * @param accessToken
     * @return
     */
    @RequestMapping(value = "/clear/{accessToken}", method = RequestMethod.GET)
    public String clear(@PathVariable("accessToken") String accessToken){
        return sercretClient.clear(accessToken);
    }

    /**
     * 获取客户端信息
     * @param accessToken
     * @return
     */
    @RequestMapping(value = "/get/{accessToken}", method = RequestMethod.GET)
    public ClientUser getClientUser(@PathVariable("accessToken") String accessToken){
        return sercretClient.getClientUser(accessToken);
    }

    /**
     * 校验客户端URL
     * @param validateClient
     * @return
     */
    @RequestMapping(value = "/validate/url", method = RequestMethod.POST)
    public String validateUrl(@RequestBody ValidateClient validateClient){
        return sercretClient.validateUrl(validateClient);
    }

}
