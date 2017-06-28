package com.kerry.wechat.api;

/**
 * 微信API接口
 * Created by wangshen on 2017/6/22.
 */
public abstract class WechatAPI {


    /**
     * 获取微信服务器IP
     * @param accessToken
     * @return
     */
    public static String getWechatServIP(String accessToken){
        return "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token="+accessToken;
    }

    /**
     * 微信access_token_url
     * @return
     */
    public static String accessTokenUrl(String appId, String appSecret){
        return "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret+"";
    }

    /**
     * 获取用户基本信息
     * @param accessToken
     * @param openId
     * @return
     */
    public static String getUserInfoUrl(String accessToken,String openId){
        return "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
    }

    /**
     * 通过code换取网页授权access_token
     * @param appId
     * @param appSecret
     * @param code
     * @return
     */
    public static String snsOauthAccessTokenUrl(String appId, String appSecret, String code){
        return "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appId+"&secret="+appSecret+"&code="+code+"&grant_type=authorization_code";
    }

    /**
     * 菜单创建
     * @param accessToken
     * @return
     */
    public static String menuCreateUrl(String accessToken){
        return "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken+"";
    }
}
