package com.kerry.wechat.api.model;

import org.dom4j.Element;

/**
 * 超链接实体类
 * Created by wangshen on 2017/6/21.
 */
public class MessageLink extends AbstractBaseMessage {
    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息描述
     */
    private String description;
    /**
     * 消息链接
     */
    private String url;
    /**
     * 消息id，64位整型
     */
    private String msgId;

    public MessageLink() {
        super();
    }

    public MessageLink(Element element) {
        toUserName = element.element("ToUserName").getStringValue();
        fromUserName = element.element("FromUserName").getStringValue();
        createTime = Long.valueOf(element.element("CreateTime").getStringValue());
        msgType = element.element("MsgType").getStringValue();
        title = element.element("Title").getStringValue();
        description = element.element("Description").getStringValue();
        url = element.element("Url").getStringValue();
        msgId = element.element("MsgId").getStringValue();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "MessageLink [title=" + title + ", description=" + description + ", url=" + url + ", msgId=" + msgId + "]";
    }
}
