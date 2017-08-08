package com.kerry.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * spring validated 解析工具
 * Created by wangshen on 2017/8/2.
 */
public class VerifyParse {

    /**
     * 解析错误信息
     * @param errorStr
     * @return
     */
    public static JSONObject parseObject(String errorStr){
        if(errorStr==null||errorStr.equals("")){
            return null;
        }
        JSONObject result = new JSONObject();
        JSONArray objects = JSON.parseArray(errorStr);
        for (Object obj : objects) {
            JSONObject objJson = JSONObject.parseObject(JSON.toJSONString(obj));
            result.put(objJson.getString("field"),objJson.getString("defaultMessage"));
        }
        return result;
    }
}
