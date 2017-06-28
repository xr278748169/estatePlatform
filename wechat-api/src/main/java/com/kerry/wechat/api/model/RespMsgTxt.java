package com.kerry.wechat.api.model;

import com.kerry.wechat.api.RespMessageType;

/**
 * 回复文本消息模型
 * Created by wangshen on 2017/6/26.
 */
public class RespMsgTxt {

    private String ToUserName;//openId
    private String FromUserName;//开发者微信号
    private Long CreateTime;//消息创建时间
    private String MsgType;//msgType
    private String Content;//消息内容

    public RespMsgTxt(){
        this.MsgType = RespMessageType.TEXT.name();
        this.CreateTime = System.currentTimeMillis();
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
