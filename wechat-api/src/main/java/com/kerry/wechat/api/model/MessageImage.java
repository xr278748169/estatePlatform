package com.kerry.wechat.api.model;

import org.dom4j.Element;

/**
 * 图片消息实体类
 * Created by wangshen on 2017/6/21.
 */
public class MessageImage extends AbstractBaseMessage {

    private String picUrl;
    private String mediaId;
    private String msgId;

    public MessageImage() {
        super();
    }

    public MessageImage(String toUserName, String fromUserName, long createTime, String msgType, String picUrl, String mediaId, String msgId) {
        super(toUserName, fromUserName, createTime, msgType);
        this.picUrl = picUrl;
        this.mediaId = mediaId;
        this.msgId = msgId;
    }

    public MessageImage(Element element) {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        mediaId = element.element("MediaId").getStringValue();
        msgId = element.element("MsgId").getStringValue();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return super.toString() + "MessageImage [picUrl=" + picUrl + ", mediaId=" + mediaId + ", msgId=" + msgId + "]";
    }
}
