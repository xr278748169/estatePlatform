package com.kerry.wechat.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.utils.HttpUtil;
import com.kerry.utils.XmlUtils;
import com.kerry.wechat.api.WechatAPI;
import com.kerry.wechat.api.WxConstant;
import com.kerry.wechat.api.inter.IWechatInter;
import com.kerry.wechat.api.model.RespMsgTxt;
import com.kerry.wechat.model.AccountModel;
import com.kerry.wechat.redis.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangshen on 2017/6/26.
 */
@Service
public class WechatService implements IWechatInter {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取公众号accessToken
     * @param accountId
     * @return
     */
    @Override
    public String getAccessToken(String accountId) {
        if(accountId.equals("")){
            return null;
        }
        String url;
        String result;
        JSONObject jsonObj;
        String accessToken = (String) redisUtil.getHash(WxConstant.WECHAT_ACCESS_TOKEN_KEY,accountId);
        if(accessToken==null||accessToken.equals("")){//获取accessToken
            jsonObj = getNewAccessToken(accountId);
            if (jsonObj == null) return null;
            accessToken = jsonObj.getString("access_token");
            redisUtil.setHash(WxConstant.WECHAT_ACCESS_TOKEN_KEY,accountId,accessToken);
        }
        //验证token是否有效
        url = WechatAPI.getWechatServIP(accessToken);
        result =  HttpUtil.httpRequest(url,HttpUtil.METHOD_TYPE_GET,null);
        jsonObj = JSON.parseObject(result);
        if(jsonObj.containsKey("errcode")){
            logger.error(" >>> 校验accessToken错误："+ WxConstant.GLOBAL_ERROR_CODE.get(jsonObj.get("errcode")));
            jsonObj = getNewAccessToken(accountId);
            if (jsonObj == null) return null;
            accessToken = jsonObj.getString("access_token");
            redisUtil.setHash(WxConstant.WECHAT_ACCESS_TOKEN_KEY,accountId,accessToken);
        }
        return accessToken;
    }

    /**
     * 微信用户关注消息
     * @param accountId
     * @param openId
     */
    @Override
    public String focusMsg(String accountId, String openId) {
        AccountModel accountModel = (AccountModel) redisUtil.getHash(WxConstant.WECHAT_ACCOUNT_KEY, accountId);
        //发送一条欢迎语
        RespMsgTxt respMsgTxt = new RespMsgTxt();
        respMsgTxt.setToUserName(openId);
        respMsgTxt.setFromUserName(accountModel.getAppid());//微信号
        respMsgTxt.setContent("欢迎关注！");
        return XmlUtils.msgEncapsulation(respMsgTxt);
    }


    //====================抽离的方法====================//
    /**
     * 获取新的accessToken
     * @param accountId
     * @return
     */
    private JSONObject getNewAccessToken(String accountId) {
        String url;
        String result;
        JSONObject jsonObj;
        AccountModel accountModel = (AccountModel) redisUtil.getHash(WxConstant.WECHAT_ACCOUNT_KEY, accountId);
        url = WechatAPI.accessTokenUrl(accountModel.getAppid(),accountModel.getAppsecret());
        result = HttpUtil.httpRequest(url,HttpUtil.METHOD_TYPE_GET,null);
        jsonObj = JSON.parseObject(result);
        if(jsonObj.containsKey("errcode")){
            logger.error(" >>> 错误："+ WxConstant.GLOBAL_ERROR_CODE.get(jsonObj.get("errcode")));
            return null;
        }
        return jsonObj;
    }
}
