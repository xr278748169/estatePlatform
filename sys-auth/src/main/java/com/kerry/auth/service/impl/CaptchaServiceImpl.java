package com.kerry.auth.service.impl;

import com.kerry.auth.redis.RedisUtil;
import com.kerry.auth.service.ICaptchaService;
import com.kerry.core.ResponseEntity;
import com.kerry.qcloud.SmsUtils;
import com.kerry.utils.PhoneUtils;
import com.kerry.utils.RandomStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 验证码服务
 * Created by wangshen on 2017/8/1.
 */
@Service
public class CaptchaServiceImpl implements ICaptchaService {

    @Autowired
    private RedisUtil redisUtil;

    //验证码有效时间10分钟
    private long AUTH_CODE_INVALID_TIME = 10*60L;

    /**
     * 生成短信验证码
      * @param telephone
     * @return
     */
    @Override
    public String generateSmsCode(String telephone) {
        if(telephone==null||telephone.equals("")){
            return ResponseEntity.createErrorJsonResponse("请输入手机号码");
        }
        if(!PhoneUtils.isPhoneLegal(telephone)){
            return ResponseEntity.createErrorJsonResponse("请输入正确的手机号码");
        }
        int authCode = RandomStr.getRandNum(0, 999999);
        redisUtil.set(telephone,authCode,AUTH_CODE_INVALID_TIME);
        ArrayList<String> params = new ArrayList<>();
        params.add(telephone);
        params.add("10");
        String result = SmsUtils.sendSms(13975, telephone, params);
        if(result.equals("ok")){
            return ResponseEntity.createNormalJsonResponse("验证码发送成功");
        }
        return ResponseEntity.createErrorJsonResponse("验证码发送失败");
    }

    /**
     * 短信验证码校验
     * @param telephone
     * @param authCode
     * @return 成功返回null
     */
    @Override
    public String verifySmsCode(String telephone, String authCode) {
        if(!redisUtil.exists(telephone)){
            return ResponseEntity.createErrorJsonResponse("验证码已失效");
        }
        String cacheCode = redisUtil.get(telephone).toString();
        if(!cacheCode.equals(authCode)){
            return ResponseEntity.createErrorJsonResponse("验证码错误");
        }
        redisUtil.remove(telephone);//校验通过移除cache
        return ResponseEntity.createNormalJsonResponse("ok");
    }
}
