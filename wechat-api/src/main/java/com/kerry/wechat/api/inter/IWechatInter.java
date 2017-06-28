package com.kerry.wechat.api.inter;

/**
 * Created by wangshen on 2017/6/26.
 */
public interface IWechatInter {

    String getAccessToken(String accountId);

    String focusMsg(String accountId, String openId);
}
