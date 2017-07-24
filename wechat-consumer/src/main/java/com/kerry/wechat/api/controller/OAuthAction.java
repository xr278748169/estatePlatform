package com.kerry.wechat.api.controller;

import com.kerry.utils.ValidityUtils;
import com.kerry.wechat.api.WxConstant;
import com.kerry.wechat.api.client.WechatUserClient;
import com.kerry.wechat.model.AccountModel;
import com.kerry.wechat.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 微信网页授权OAuth 2.0处理器
 * Created by wangshen on 2017/7/24.
 */
@Controller
@RequestMapping("/api/wechat/oauth")
public class OAuthAction {

    private static final Logger logger = LoggerFactory.getLogger(OAuthAction.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WechatUserClient wechatUserClient;

    private String httpURL = "http://192.168.25.110:8080";

    /**
     * 微信页面转发控制器
     * @param accountId
     * @param code 用户授权code
     * @param state 页面标识
     * @return
     */
    @RequestMapping(value = "/{accountId}/page", method = RequestMethod.GET)
    public String pageController(@PathVariable String accountId, @RequestParam("code") String code, @RequestParam("state") String state){
        if(accountId==null || accountId.equals("")){
            return "redirect:"+httpURL;
        }
        AccountModel accountModel = (AccountModel) redisUtil.getHash(WxConstant.WECHAT_ACCOUNT_KEY,accountId);
        //判断请求是否携带appid，并且验证携带的appid是否为系统注册的公众号
        if(!ValidityUtils.validityStr(new String[] { state, code }) || accountModel == null){
            return "redirect:"+httpURL;
        }
        String token = wechatUserClient.oauthUser(code, accountId);
        if(token.equals("")){
            return "redirect:"+httpURL;
        }
        //获取state的两个参数
        String[] params = state.split("@");
        return "redirect:"+httpURL+"?token="+token+"&redirect="+params[0]+"&redirect_tab="+params[1];
    }
}
