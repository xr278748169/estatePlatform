package com.kerry.wechat.api.inter;

/**
 * Created by wangshen on 2017/6/21.
 */
public interface IWechatUserInter {

    String oauthUser(String code, String accountId) throws Exception;
}
