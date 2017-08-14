package com.kerry.wechat.api.inter;

import com.kerry.dto.WechatCache;

/**
 * 微信用户cache
 * Created by wangshen on 2017/7/24.
 */
public interface IWechatUserCacheInter {

    String setUserCache(String openId, String accountId) throws Exception;

    WechatCache getUserCache(String token) throws Exception;

    String updateUserCache(WechatCache wechatCache) throws Exception;
}
