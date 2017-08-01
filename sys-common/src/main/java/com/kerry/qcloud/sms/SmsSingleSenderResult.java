package com.kerry.qcloud.sms;


import com.alibaba.fastjson.JSONObject;

public class SmsSingleSenderResult {
/*
{
    "result": 0,
    "errmsg": "OK", 
    "ext": "", 
    "sid": "xxxxxxx", 
    "fee": 1
}
 */
	public int result;
	public String errMsg = "";
	public String ext = "";
	public String sid = "";
	public int fee;
	
	public String toString() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result",result);
        jsonObj.put("errMsg",errMsg);
        jsonObj.put("ext",ext);
        jsonObj.put("sid",sid);
        jsonObj.put("fee",fee);
		return jsonObj.toString();
	}
}
