package com.kerry.wechat.api.inter;

import com.kerry.wechat.model.TUserModel;

/**
 * 微信用户cache
 * Created by wangshen on 2017/7/24.
 */
public interface IWechatUserCacheInter {

    String setUserCache(String openId, String accountId);

    String getUserCache(String token);
}
