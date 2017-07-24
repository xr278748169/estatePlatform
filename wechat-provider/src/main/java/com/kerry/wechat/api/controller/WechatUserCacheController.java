package com.kerry.wechat.api.controller;

import com.kerry.wechat.api.inter.IWechatUserCacheInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信用户cache
 * Created by wangshen on 2017/7/24.
 */
@RestController
@RequestMapping("/wechat/user/cache")
public class WechatUserCacheController {

    @Autowired
    private IWechatUserCacheInter wechatUserCacheInter;

    /**
     * 设置微信用户cache
     * @param openId
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/{accountId}/{openId}/set", method = RequestMethod.GET)
    public String setUserCache(@PathVariable("openId") String openId, @PathVariable("accountId") String accountId) throws Exception {
        return wechatUserCacheInter.setUserCache(openId, accountId);
    }

    /**
     * 获取微信用户cache
     * @param token
     * @return
     */
    @RequestMapping(value = "/{token}/get", method = RequestMethod.GET)
    public String getUserCache(@PathVariable("token") String token) throws Exception {
        return wechatUserCacheInter.getUserCache(token);
    }
}
