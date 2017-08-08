package com.kerry.estate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.core.ResponseEntity;
import com.kerry.core.VerifyParse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.util.Set;

/**
 * 注入校验器
 * Created by wangshen on 2017/8/2.
 */
@Configuration
@EnableAutoConfiguration
public class ValidFactory {

    /**
     * 注入校验器
     * @return
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){

        return new MethodValidationPostProcessor();
    }

    /**
     * 根据要求解析返回的错误
     * @param result
     * @return
     */
    public static String message(BindingResult result){
        StringBuffer sb = new StringBuffer();
        JSONObject errorJson = VerifyParse.parseObject(JSON.toJSONString(result.getFieldErrors()));
        Set<String> keySet = errorJson.keySet();
        for (String key: keySet) {
            sb.append(errorJson.get(key)+" ");
        }
        if(errorJson!=null){
            return ResponseEntity.createErrorJsonResponse(sb.toString());
        }
        return ResponseEntity.createErrorJsonResponse("");
    }

}
