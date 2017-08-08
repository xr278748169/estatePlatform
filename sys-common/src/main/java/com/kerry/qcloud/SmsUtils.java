package com.kerry.qcloud;

import com.alibaba.fastjson.JSONObject;
import com.kerry.qcloud.sms.SmsSingleSender;
import com.kerry.qcloud.sms.SmsSingleSenderResult;

import java.util.ArrayList;

/**
 * 短信工具
 * Created by wangshen on 2017/8/1.
 */
public class SmsUtils {

    //应用编号
    private final static int APP_ID = 1400023963;
    //应用key
    private final static String APP_KEY = "1dcd15920fe20dfda40128b78185661a";

    /**
     * 短信单发
     * @param tmplId
     * @param phone
     * @param params
     * @return
     */
    public static String sendSms(int tmplId, String phone, ArrayList<String> params){
        try {
            SmsSingleSender singleSender = new SmsSingleSender(APP_ID, APP_KEY);
            SmsSingleSenderResult singleSenderResult;
            singleSenderResult = singleSender.sendWithParam("86", phone, tmplId, params, "", "", "");
            JSONObject resultJson = JSONObject.parseObject(singleSenderResult.toString());
            if(resultJson.containsKey("errMsg")&&resultJson.get("errMsg").equals("OK")){
                return "ok";
            }
            return "";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

}
