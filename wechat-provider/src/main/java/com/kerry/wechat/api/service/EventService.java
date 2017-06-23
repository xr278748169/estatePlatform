package com.kerry.wechat.api.service;

import com.kerry.wechat.api.WxConstant;
import com.kerry.wechat.api.inter.IEventInter;
import com.kerry.wechat.model.AccountModel;
import com.kerry.wechat.redis.RedisUtil;
import org.apache.log4j.Logger;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 用户关注
     * @param element
     * @param accountModel
     * @return
     */
    @Override
    public String focus(Element element, AccountModel accountModel) {
        logger.debug(" >>> 用户关注消息 --> "+element.asXML());
        String openId = element.elementText("FromUserName");//获得openId
        String accessToken = (String) redisUtil.getHash(WxConstant.WECHAT_ACCESS_TOKEN_KEY,accountModel.getAccountId());

        return "";
    }
}
