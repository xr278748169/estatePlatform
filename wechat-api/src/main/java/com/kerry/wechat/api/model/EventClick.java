package com.kerry.wechat.api.model;

import org.dom4j.Element;

/**
 * 用户单击菜单事件
 * Created by wangshen on 2017/6/21.
 */
public class EventClick extends AbstractBaseMessage {

    protected String event;
    protected String eventKey;

    public EventClick() {
        super();
    }

    public EventClick(long createTime, String fromUserName, String toUserName, String msgType, String event, String eventKey) {
        super();
        super.createTime = createTime;
        super.fromUserName = fromUserName;
        super.msgType = msgType;
        super.toUserName = toUserName;
        this.event = event;
        this.eventKey = eventKey;
    }

    /**
     * 根据Element对象生成点击事件对象
     *
     * @param element
     */
    public EventClick(Element element) {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        event = element.element("Event").getStringValue();
        eventKey = element.element("EventKey").getStringValue();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    @Override
    public String toString() {
        return "EventClick [event=" + event + ", eventKey=" + eventKey + "]";
    }
}
