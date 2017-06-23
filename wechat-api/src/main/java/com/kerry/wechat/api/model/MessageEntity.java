package com.kerry.wechat.api.model;

import java.util.List;

/**
 * Created by wangshen on 2017/6/21.
 */
public class MessageEntity {

    private String toUserName;

    private String fromUserName;

    private List<Articles> articles;

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
    public List<Articles> getArticles() {
        return articles;
    }
    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "MessageEntity [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", articles=" + articles + "]";
    }
}
