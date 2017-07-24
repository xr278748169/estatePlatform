package com.kerry.wechat.api.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.utils.HttpUtil;
import com.kerry.wechat.api.WechatAPI;
import com.kerry.wechat.api.WxConstant;
import com.kerry.wechat.api.inter.IEventInter;
import com.kerry.wechat.api.inter.IWechatInter;
import com.kerry.wechat.api.model.EventScan;
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
import java.util.List;

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
     * @param openId
     * @param accountId
     * @return
     */
    @Override
    public String focus(String openId, String accountId) throws Exception {
        String url;
        String result;
        JSONObject jsonObj;
        String accessToken = wechatInter.getAccessToken(accountId);
        url = WechatAPI.getUserInfoUrl(accessToken,openId);
        result = HttpUtil.httpRequest(url,HttpUtil.METHOD_TYPE_GET, null);
        jsonObj = JSON.parseObject(result);
        if(jsonObj.containsKey("errcode")){
            logger.error(" >>> 获取微信用户信息错误："+ WxConstant.GLOBAL_ERROR_CODE.get(jsonObj.get("errcode")));
        }else{
            logger.debug(" >>> 已获取到用户信息："+jsonObj.toJSONString());
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
            tUser.setAccountId(accountId);
            //设置系统级别参数
            tUser.setIsFocus("1");
            tUser.setFocusTime(new Date());
            //查询用户关注信息是否存在
            TUserModel params = new TUserModel();
            params.setOpenId(jsonObj.getString("openid"));
            params.setAccountId(accountId);
            List<TUserModel> tUserList = itUserInter.findByCondition(params);
            if(tUserList.size() > 0){
                tUser.setTuId(tUserList.get(0).getTuId());
                itUserInter.update(tUser);
            }else{
                itUserInter.insert(tUser);
            }
            //调用回复消息
            return wechatInter.focusMsg(accountId,openId);
        }
        return "fail";
    }

    /**
     * 用户取消了关注
     * @param openId
     * @param accountId
     * @return
     * @throws Exception
     */
    @Override
    public String unFocus(String openId, String accountId) throws Exception {
        TUserModel params = new TUserModel();
        params.setOpenId(openId);
        params.setAccountId(accountId);
        List<TUserModel> tUserList = itUserInter.findByCondition(params);
        if(tUserList.size() > 0){
            String tuId = tUserList.get(0).getTuId();
            TUserModel tUser = new TUserModel();
            tUser.setTuId(tuId);
            tUser.setSubscribe("0");
            tUser.setIsFocus("0");
            itUserInter.update(tUser);
            return "";
        }
        return "fail";
    }

    /**
     * 用户扫码
     * @param eventScan
     * @return
     * @throws Exception
     */
    @Override
    public String scanQrCode(EventScan eventScan) throws Exception {
        return null;
    }
}
