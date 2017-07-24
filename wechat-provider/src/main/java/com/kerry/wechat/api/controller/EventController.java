package com.kerry.wechat.api.controller;

import com.kerry.wechat.api.inter.IEventInter;
import com.kerry.wechat.model.AccountModel;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信事件处理
 * Created by wangshen on 2017/6/26.
 */
@RestController
@RequestMapping("/wechat/event")
public class EventController {

    @Autowired
    private IEventInter eventInter;

    /**
     * 用户关注操作
     * @param openId
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/{accountId}/{openId}/focus", method = RequestMethod.GET)
    public String focus(@PathVariable("openId") String openId, @PathVariable("accountId") String accountId) throws Exception {
        return eventInter.focus(openId,accountId);
    }

    /**
     * 用户取消关注操作
     * @param openId
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/{accountId}/{openId}/unFocus", method = RequestMethod.GET)
    public String unFocus(@PathVariable("openId") String openId, @PathVariable("accountId") String accountId) throws Exception {
        return eventInter.unFocus(openId, accountId);
    }
}
