package com.kerry.wechat.api.model;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 用户扫描事件
 * Created by wangshen on 2017/6/21.
 */
public class EventScan extends AbstractBaseMessage {

    /**
     * 事件类型，subscribe/SCAN
     */
    private String event;
    /**
     * 用户已关注时:事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id <BR>
     * 用户未关注时:事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    private String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private String ticket;

    public EventScan() {
        super();
    }

    public EventScan(long createTime, String fromUserName, String toUserName, String msgType, String event, String eventKey, String ticket) {
        super(toUserName, fromUserName, createTime, msgType);
        this.event = event;
        this.eventKey = eventKey;
        this.ticket = ticket;
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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     *
     * create a instance EventScan.
     *
     * @param element
     * @throws DocumentException
     */
    public EventScan(Element element) throws DocumentException {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        event = element.element("Event").getStringValue();
        eventKey = element.element("EventKey").getStringValue();
        ticket = element.element("Ticket").getStringValue();
    }
}
