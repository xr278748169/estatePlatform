package com.kerry.wechat.api.model;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 语音消息
 * Created by wangshen on 2017/6/21.
 */
public class MessageVoice extends AbstractBaseMessage {

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private String mediaId;
    /**
     * 语音格式，如amr，speex等
     */
    private String format;
    /**
     * 消息id，64位整型
     */
    private String msgId;

    public MessageVoice() {
        super();
    }

    public MessageVoice(String toUserName, String fromUserName, Long createTime, String msgType, String mediaId, String format, String msgId) {
        super(toUserName, fromUserName, createTime, msgType);
        this.mediaId = mediaId;
        this.format = format;
        this.msgId = msgId;
    }

    public MessageVoice(Element element) throws DocumentException {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        mediaId = element.element("MediaId").getStringValue();
        format = element.element("Format").getStringValue();
        msgId = element.element("MsgId").getStringValue();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "MessageVoice [mediaId=" + mediaId + ", format=" + format + ", msgId=" + msgId + "]";
    }
}
