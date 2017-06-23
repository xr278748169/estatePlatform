package com.kerry.wechat.api.model;

/**
 * 微信接口基础消息
 * Created by wangshen on 2017/6/21.
 */
public abstract class AbstractBaseMessage {

    /**
     * 微信公众平台服务器
     */
    protected String toUserName;
    /**
     * 发送消息的用户
     */
    protected String fromUserName;
    /**
     * 消息的创建时间，毫秒
     */
    protected Long createTime;
    /**
     * 消息的类型
     */
    protected String msgType;

    public AbstractBaseMessage() {
        super();
    }

    public AbstractBaseMessage(String toUserName, String fromUserName, Long createTime, String msgType) {
        this.toUserName = toUserName;
        this.fromUserName = fromUserName;
        this.createTime = createTime;
        this.msgType = msgType;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
