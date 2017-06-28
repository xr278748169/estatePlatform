package com.kerry.wechat.api.controller;

import com.kerry.wechat.api.inter.IEventInter;
import com.kerry.wechat.model.AccountModel;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * @param element
     * @param accountModel
     * @return
     */
    @RequestMapping(value = "/focus", method = RequestMethod.POST)
    public String focus(@RequestBody Element element, @RequestBody AccountModel accountModel) throws Exception {
        return eventInter.focus(element,accountModel);
    }
}
