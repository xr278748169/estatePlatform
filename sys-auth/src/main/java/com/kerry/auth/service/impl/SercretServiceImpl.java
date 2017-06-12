package com.kerry.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kerry.auth.redis.RedisUtil;
import com.kerry.auth.service.SercretService;
import com.kerry.core.ResponseEntity;
import com.kerry.model.ClientUser;
import com.kerry.model.ValidateClient;
import com.kerry.utils.RandomStr;
import com.kerry.utils.SecurityUtil;
import com.kerry.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by wangshen on 2017/5/24.
 */
@Service
public class SercretServiceImpl implements SercretService {

    @Autowired
    private RedisUtil redisUtil;

    //登录有效时间2小时
    private long LOGIN_INVALID_TIME = 2*60*60L;

    /**
     * 生成连接校验码
     * @param clientType 客户端类型
     * @param uiSign 客户端标示
     * @return
     */
    @Override
    public String generater(String clientType,String uiSign) {
        if(clientType.equals("")||uiSign.equals("")){
            return ResponseEntity.createErrorJsonResponse("系统异常");
        }
        String sign = RandomStr.getRandomStr(128);
        String accessToken = StringUtils.filterSpecialChar(SecurityUtil.AESEncode(sign,uiSign));
        ClientUser clientUser = new ClientUser();
        clientUser.setClientId(uiSign);
        clientUser.setClientType(clientType);
        clientUser.setSign(sign);
        clientUser.setAccessToken(accessToken);
        redisUtil.set(accessToken,clientUser,LOGIN_INVALID_TIME);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("accessToken",accessToken);
        return ResponseEntity.createNormalJsonResponse(jsonObj);
    }

    /**
     * 用户退出或者刷新系统后，清空服务端用户信息
     * @param accessToken
     * @return
     */
    @Override
    public String clear(String accessToken) {
        if(!redisUtil.exists(accessToken)){
            redisUtil.remove(accessToken);
        }
        return ResponseEntity.createNormalJsonResponse("清空连接信息成功");
    }

    /**
     * 获取客户端用户信息
     * @param accessToken
     * @return
     */
    @Override
    public ClientUser getClientUser(String accessToken) {
        if(redisUtil.exists(accessToken)){
           return (ClientUser) redisUtil.get(accessToken);
        }
        return null;
    }

    /**
     * 更新客户端用户信息
     * @param clientUser
     * @return
     */
    @Override
    public String updateClientUser(ClientUser clientUser) {
        if(!redisUtil.exists(clientUser.getAccessToken())){
            return ResponseEntity.createErrorJsonResponse("连接信息不存在");
        }
        redisUtil.set(clientUser.getAccessToken(),clientUser,LOGIN_INVALID_TIME);
        return ResponseEntity.createNormalJsonResponse("更新成功");
    }

    /**
     * 客户端URL校验
     * @param validateClient
     * @return
     */
    @Override
    public String validateUrl(ValidateClient validateClient) {
        if(!redisUtil.exists(validateClient.getAccessToken())){
            return "-1";//重新登录
        }
        //校验URL是否有权限
        ClientUser clientUser = (ClientUser) redisUtil.get(validateClient.getAccessToken());
        Map<String,Object> resMap = clientUser.getUserRes();
        if(!resMap.containsKey(validateClient.getWebUrl())){
            return "403";//403
        }
        return "200";
    }
}
