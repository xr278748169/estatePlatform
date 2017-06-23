package com.kerry.wechat.api.model;

import org.dom4j.Element;

/**
 * 用户发送地理位置事件
 * Created by wangshen on 2017/6/21.
 */
public class EventLocation extends AbstractBaseMessage {


    private String event;

    private Double latitude;

    private Double longitude;

    private Double precision;

    public EventLocation(Element element) {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        event = element.element("Event").getStringValue();
        latitude = Double.parseDouble(element.element("Latitude").getStringValue());
        longitude = Double.parseDouble(element.element("Longitude").getStringValue());
        precision = Double.parseDouble(element.element("Precision").getStringValue());
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }
}
