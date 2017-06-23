package com.kerry.wechat.api.model;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 用户扫描二维码并关注微信号事件
 * Created by wangshen on 2017/6/21.
 */
public class EventSubscribe extends AbstractBaseMessage {

    protected String event;

    public EventSubscribe() {
        super();
    }

    public EventSubscribe(String toUserName, String fromUserName, Long createTime, String msgType, String event) {
        super(toUserName, fromUserName, createTime, msgType);
        this.event = event;
    }


    public EventSubscribe(Element element) throws DocumentException {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        event = element.element("Event").getStringValue();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return super.toString() + "EventSubscribe [event=" + event + "]";
    }
}
