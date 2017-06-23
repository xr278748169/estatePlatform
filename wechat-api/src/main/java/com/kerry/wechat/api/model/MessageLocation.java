package com.kerry.wechat.api.model;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 地理位置消息
 * Created by wangshen on 2017/6/21.
 */
public class MessageLocation extends AbstractBaseMessage {

    /**
     * 地理位置维度
     */
    private String location_x;
    /**
     * 地理位置经度
     */
    private String location_y;
    /**
     * 地图缩放大小
     */
    private String scale;
    /**
     * 地理位置信息
     */
    private String label;
    /**
     * 消息id，64位整型
     */
    private String msgId;

    public MessageLocation() {
        super();
    }

    public MessageLocation(String toUserName, String fromUserName, Long createTime, String msgType, String location_x, String location_y, String scale, String label, String msgId) {
        super(toUserName, fromUserName, createTime, msgType);
        this.location_x = location_x;
        this.location_y = location_y;
        this.scale = scale;
        this.label = label;
        this.msgId = msgId;
    }

    public MessageLocation(Element element) throws DocumentException {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        location_x = element.element("Location_X").getStringValue();
        location_y = element.element("Location_Y").getStringValue();
        scale = element.element("Scale").getStringValue();
        label = element.element("Label").getStringValue();
        msgId = element.element("MsgId").getStringValue();
    }

    public String getLocation_x() {
        return location_x;
    }

    public void setLocation_x(String location_x) {
        this.location_x = location_x;
    }

    public String getLocation_y() {
        return location_y;
    }

    public void setLocation_y(String location_y) {
        this.location_y = location_y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "MessageLocation [location_x=" + location_x + ", location_y=" + location_y + ", scale=" + scale + ", label=" + label + ", msgId=" + msgId + "]";
    }

}
