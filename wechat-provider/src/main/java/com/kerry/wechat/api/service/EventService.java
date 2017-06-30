package com.kerry.wechat.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.utils.HttpUtil;
import com.kerry.wechat.api.WechatAPI;
import com.kerry.wechat.api.WxConstant;
import com.kerry.wechat.api.inter.IEventInter;
import com.kerry.wechat.api.inter.IWechatInter;
import com.kerry.wechat.api.model.WechatUser;
import com.kerry.wechat.inter.ITUserInter;
import com.kerry.wechat.model.AccountModel;
import com.kerry.wechat.model.TUserModel;
import com.kerry.wechat.redis.RedisUtil;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.util.Date;

/**
 * 微信事件处理
 * Created by wangshen on 2017/6/22.
 */
@Service
@Transactional("txManager")
public class EventService implements IEventInter {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IWechatInter wechatInter;

    @Autowired
    private ITUserInter itUserInter;

    /**
     * 用户关注
     * @param element
     * @param accountId
     * @return
     */
    @Override
    public String focus(Element element, String accountId) throws Exception {
        logger.debug(" >>> 用户关注消息 --> "+element.asXML());
        String url;
        String result;
        JSONObject jsonObj;
        String openId = element.elementText("FromUserName");//获得openId
        String accessToken = wechatInter.getAccessToken(accountId);
        url = WechatAPI.getUserInfoUrl(accessToken,openId);
        result = HttpUtil.httpRequest(url,HttpUtil.METHOD_TYPE_GET, null);
        jsonObj = JSON.parseObject(result);
        if(jsonObj.containsKey("errcode")){
            logger.error(" >>> 获取微信用户信息错误："+ WxConstant.GLOBAL_ERROR_CODE.get(jsonObj.get("errcode")));
        }else{
            logger.debug(" >>> 已获取到用户信息："+jsonObj.toJSONString());
            /**
             * 保存关注信息
             */
            TUserModel tUser = new TUserModel();
            tUser.setSubscribe(jsonObj.getString("subscribe"));//是否关注
            tUser.setOpenId(jsonObj.getString("openid"));//openId
            tUser.setNickName(URLEncoder.encode(jsonObj.getString("nickname")));//昵称 转义存储
            tUser.setSex(jsonObj.getString("sex"));//性别
            tUser.setCity(jsonObj.getString("city"));
            tUser.setCountry(jsonObj.getString("country"));
            tUser.setProvince(jsonObj.getString("province"));
            tUser.setLanguage(jsonObj.getString("language"));
            tUser.setHandimgUrl(jsonObj.getString("headimgurl"));
            tUser.setSubscribeTime(jsonObj.getString("subscribe_time"));
            tUser.setUnionid(jsonObj.getString("unionid"));
            tUser.setRemarks(jsonObj.getString("remark"));
            //设置系统级别参数
            tUser.setIsFocus("1");
            tUser.setFocusTime(new Date());
            itUserInter.insert(tUser);
            //调用回复消息
            return wechatInter.focusMsg(accessToken,openId);
        }
        return "";
    }
}
