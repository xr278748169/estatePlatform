package com.kerry.wechat.api;

/**
 * 微信发送消息类型
 * Created by wangshen on 2017/6/26.
 */
public enum RespMessageType {

    TEXT("text", 1), // 文本消息
    NEWS("news", 2), // 图文消息
    IMAGE("image", 3);// 图片

    @SuppressWarnings("unused")
    private String name;
    @SuppressWarnings("unused")
    private int index;

    RespMessageType(String name, int index) {
        this.name = name;
        this.index = index;
    }
}
