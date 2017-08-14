package com.kerry.wechat.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.utils.HttpUtil;
import com.kerry.utils.MD5Util;
import com.kerry.wechat.api.WechatAPI;
import com.kerry.wechat.api.WxConstant;
import com.kerry.wechat.api.inter.IWechatUserCacheInter;
import com.kerry.wechat.api.inter.IWechatUserInter;
import com.kerry.wechat.inter.ITUserInter;
import com.kerry.wechat.model.AccountModel;
import com.kerry.wechat.model.TUserModel;
import com.kerry.wechat.redis.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 微信用户
 * Created by wangshen on 2017/7/24.
 */
@Service
@Transactional("txManager")
public class WechatUserService implements IWechatUserInter {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ITUserInter itUserInter;

    @Autowired
    private IWechatUserCacheInter wechatUserCacheInter;

    /**
     * 获取用户授权信息
     * @param code
     * @param accountId
     * @return
     */
    @Override
    public String oauthUser(String code, String accountId) throws Exception {
        AccountModel accountModel = (AccountModel) redisUtil.getHash(WxConstant.WECHAT_ACCOUNT_KEY,accountId);
        String appSecret = accountModel.getAppsecret();
        String appId = accountModel.getAppid();
        String url = WechatAPI.snsOauthAccessTokenUrl(appId, appSecret, code);
        String result =  HttpUtil.httpRequest(url,HttpUtil.METHOD_TYPE_GET,null);
        JSONObject jsonObj = JSON.parseObject(result);
        if(jsonObj.containsKey("errcode")) {
            logger.error(" >>> 获得授权网页临时access_token错误：：" + WxConstant.GLOBAL_ERROR_CODE.get(jsonObj.get("errcode")));
        }
        String openId = jsonObj.getString(WxConstant.APPLICATION_OPENID_KEY);
        TUserModel params = new TUserModel();
        params.setOpenId(openId);
        params.setAccountId(accountId);
        List<TUserModel> tUserList = itUserInter.findByCondition(params);
        if(tUserList.size() > 0){
            //这里生成一个临时token，缓存30分钟，处理微信前端信息，避免直接暴露用户ID等信息
            String token = wechatUserCacheInter.setUserCache(openId,accountId);
            if(token!=null && !token.equals("")){
                return token;
            }
        }
        return "";//失败
    }

}
