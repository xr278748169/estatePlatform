package com.kerry.wechat.api.service;

import com.alibaba.fastjson.JSONObject;
import com.kerry.utils.MD5Util;
import com.kerry.wechat.api.inter.IWechatUserCacheInter;
import com.kerry.wechat.inter.ITUserInter;
import com.kerry.wechat.model.TUserModel;
import com.kerry.wechat.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private long LOGIN_INVALID_TIME = 30*60L;

    /**
     * 设置微信用户cache
     * @param openId
     * @return
     */
    @Override
    public String setUserCache(String openId, String accountId) {
        TUserModel params = new TUserModel();
        params.setOpenId(openId);
        params.setAccountId(accountId);
        List<TUserModel> tUserList = itUserInter.findByCondition(params);
        if(tUserList.size() > 0){
            TUserModel tUser = tUserList.get(0);
            String userKey = MD5Util.string2MD5(openId+accountId).toUpperCase();
            JSONObject cache = new JSONObject();
            cache.put("token", userKey);
            cache.put("openId", openId);
            cache.put("tuId", tUser.getTuId());
            cache.put("accountId", accountId);
            cache.put("authBuss", tUser.getAuthBuss());
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
    public String getUserCache(String token) {
        if(!redisUtil.exists(token)){
            return null;
        }
        JSONObject cache = (JSONObject) redisUtil.get(token);
        return cache.toJSONString();
    }
}
