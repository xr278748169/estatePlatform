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

    public static String message(BindingResult result){
        JSONObject errorJson = VerifyParse.parseObject(JSON.toJSONString(result.getFieldErrors()));
        if(errorJson!=null){
            return ResponseEntity.createErrorJsonResponse(errorJson);
        }
        return ResponseEntity.createErrorJsonResponse("");
    }

}
