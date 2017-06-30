package com.kerry.wechat.api.inter;

import org.dom4j.Element;

/**
 * 微信事件类消息接口
 * Created by wangshen on 2017/6/21.
 */
public interface IEventInter {

    /**
     * 用户关注操作
     * @param element
     * @return
     */
    String focus(Element element, String accountId) throws Exception;

}
