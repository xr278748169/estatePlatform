package com.kerry.wechat.api.model;

import org.dom4j.Element;

/**
 * 点击菜单跳转链接时的事件推送
 * Created by wangshen on 2017/6/21.
 */
public class EventView extends AbstractBaseMessage {

    protected String eventKey;// 事件KEY值，设置的跳转URL
    protected String event; // 事件类型，VIEW

    public EventView() {
        super();
    }

    public EventView(String fromUserName, String toUserName, long createTime, String event, String eventKey) {
        super();
        this.event = event;
        this.eventKey = eventKey;
        super.createTime = createTime;
        super.fromUserName = fromUserName;
        super.msgType = msgType;
        super.toUserName = toUserName;
    }

    public EventView(Element element) {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        event = element.element("Event").getStringValue();
        eventKey = element.element("EventKey").getStringValue();
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "EventView [eventKey=" + eventKey + ", event=" + event + "]";
    }
}
