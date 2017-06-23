package com.kerry.wechat.api.model;

import org.dom4j.DocumentException;
import org.dom4j.Element;

/**
 * 普通文本消息
 * Created by wangshen on 2017/6/21.
 */
public class MessageText extends AbstractBaseMessage {

    /**
     * 文本消息内容
     */
    protected String content;
    /**
     * 文本消息编号
     */
    protected Long msgId;

    public MessageText() {
        super();
    }

    public MessageText(String toUserName, String fromUserName, Long createTime, String msgType, String content, Long msgId) {
        super(toUserName, fromUserName, createTime, msgType);
        this.content = content;
        this.msgId = msgId;
    }
    /**
     * 封装接收到的消息
     * @param element
     * @throws DocumentException
     */
    public MessageText(Element element) throws DocumentException {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        content = element.element("Content").getStringValue();
        msgId = Long.valueOf(element.element("MsgId").getStringValue());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "MessageText [content=" + content + ", msgId=" + msgId + "]";
    }

}
