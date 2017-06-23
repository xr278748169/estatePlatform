package com.kerry.wechat.api;

/**
 * 微信发送消息类型
 * Created by wangshen on 2017/6/22.
 */
public enum MessageType {

    // text, image, voice, video, location,link, event;
    TEXT("text", 1), IMAGE("image", 2), VOICE("voice", 3), VIDEO("video", 4), LOCATION("location", 5), LINK("link", 6), EVENT("event", 7), SUBSCRIBE("subscribe", 8), UNSUBSCRIBE("unsubscribe", 9), SCAN("SCAN", 10), ELOCATION("LOCATION", 11), CLICK("CLICK", 12), VIEW("VIEW", 13), TEMPLATESENDJOBFINISH("TEMPLATESENDJOBFINISH", 14);
    private String name;
    @SuppressWarnings("unused")
    private int index;

    private MessageType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static MessageType getObjectByName(String name) {
        for (MessageType param : MessageType.values()) {
            if (param.name.equals(name))
                return param;
        }
        return null;
    }
}
