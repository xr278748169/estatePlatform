package com.kerry.wechat.api.model;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 视频消息
 * Created by wangshen on 2017/6/21.
 */
public class MessageVideo extends AbstractBaseMessage {

    /**
     * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String mediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String thumbMediaId;
    /**
     * 消息id，64位整型
     */
    private String msgId;

    public MessageVideo() {
        super();
    }

    public MessageVideo(String toUserName, String fromUserName, Long createTime, String msgType, String mediaId, String thumbMediaId, String msgId) {
        super(toUserName, fromUserName, createTime, msgType);
        this.mediaId = mediaId;
        this.thumbMediaId = thumbMediaId;
        this.msgId = msgId;
    }

    public MessageVideo(Element element) throws DocumentException {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        mediaId = element.element("MediaId").getStringValue();
        thumbMediaId = element.element("ThumbMediaId").getStringValue();
        msgId = element.element("MsgId").getStringValue();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "MessageVideo [mediaId=" + mediaId + ", thumbMediaId=" + thumbMediaId + ", msgId=" + msgId + "]";
    }
}
