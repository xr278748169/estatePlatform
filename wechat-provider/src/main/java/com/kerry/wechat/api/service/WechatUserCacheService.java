package com.kerry.wechat.api.service;

import com.alibaba.fastjson.JSONObject;
import com.kerry.dto.WechatCache;
import com.kerry.utils.MD5Util;
import com.kerry.wechat.api.inter.IWechatUserCacheInter;
import com.kerry.wechat.inter.ITUserInter;
import com.kerry.wechat.model.TUserModel;
import com.kerry.wechat.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLDecoder;
import java.util.List;

/**
 * 微信用户cache，这里处理一下与业务系统的信息
 * Created by wangshen on 2017/7/24.
 */
@Service
@Transactional("txManager")
public class WechatUserCacheService implements IWechatUserCacheInter {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ITUserInter itUserInter;

    //微信用户cache有效时间2小时
    private long LOGIN_INVALID_TIME = 2*60*60L;

    /**
     * 设置微信用户cache
     * @param openId
     * @return
     */
    @Override
    public String setUserCache(String openId, String accountId) throws Exception {
        TUserModel params = new TUserModel();
        params.setOpenId(openId);
        params.setAccountId(accountId);
        List<TUserModel> tUserList = itUserInter.findByCondition(params);
        if(tUserList.size() > 0){
            TUserModel tUser = tUserList.get(0);
            String userKey = MD5Util.string2MD5(openId+accountId);
            WechatCache cache = new WechatCache();
            cache.setAccountId(accountId);
            cache.setTuId(tUser.getTuId());
            cache.setAuthState(tUser.getAuthBuss());
            cache.setToken(userKey);
            cache.setBussId("");
            cache.setNickName(URLDecoder.decode(tUser.getNickName()));
            cache.setHeaderImg(tUser.getHandimgUrl());
            redisUtil.set(userKey, cache, LOGIN_INVALID_TIME);
            return userKey;
        }
        return "";
    }

    /**
     * 获取微信用户cache
     * @param token
     * @return
     */
    @Override
    public WechatCache getUserCache(String token) throws Exception {
        if(!redisUtil.exists(token)){
            return null;
        }
        WechatCache cache = (WechatCache) redisUtil.get(token);
        return cache;
    }

    /**
     * 更新微信用户cache
     * @param wechatCache
     * @return
     */
    @Override
    public String updateUserCache(WechatCache wechatCache) throws Exception {
        String token = wechatCache.getToken();
        if(!redisUtil.exists(token)){
           return "";
        }
        redisUtil.set(token, wechatCache, LOGIN_INVALID_TIME);
        return "OK";
    }
}
