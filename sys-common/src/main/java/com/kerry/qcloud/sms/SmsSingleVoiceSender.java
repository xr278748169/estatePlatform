// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SmsSingleVoiceSender.java

package com.kerry.qcloud.sms;

import java.io.*;
import java.net.HttpURLConnection;
import org.json.JSONObject;

// Referenced classes of package com.qcloud.sms:
//			SmsSenderUtil, SmsSingleVoiceSenderResult

public class SmsSingleVoiceSender
{

	int appid;
	String appkey;
	String url;
	SmsSenderUtil util;

	public SmsSingleVoiceSender(int appid, String appkey)
	{
		url = "https://test.tim.qq.com/v3/tlsvoicesvr/sendvoiceprompt";
		util = new SmsSenderUtil();
		this.appid = appid;
		this.appkey = appkey;
	}

	public SmsSingleVoiceSenderResult send(String nationCode, String phoneNumber, int type, String fileName, String ext)
		throws Exception
	{
		if (3 != type)
			throw new Exception((new StringBuilder("type ")).append(type).append(" error").toString());
		if (ext == null)
			ext = "";
		JSONObject data = new JSONObject();
		JSONObject tel = new JSONObject();
		tel.put("nationcode", nationCode);
		tel.put("phone", phoneNumber);
		data.put("tel", tel);
		data.put("prompttype", type);
		data.put("promptfile", fileName);
		data.put("sig", util.stringMD5((new StringBuilder(String.valueOf(appkey))).append(phoneNumber).toString()));
		data.put("ext", ext);
		String wholeUrl = String.format("%s?sdkappid=%d", new Object[] {
			url, Integer.valueOf(appid)
		});
		HttpURLConnection conn = util.getPostHttpConn(wholeUrl);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
		System.out.println(data.toString());
		wr.write(data.toString());
		wr.flush();
		StringBuilder sb = new StringBuilder();
		int httpRspCode = conn.getResponseCode();
		SmsSingleVoiceSenderResult result;
		if (httpRspCode == 200)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			for (String line = null; (line = br.readLine()) != null;)
				sb.append(line);

			br.close();
			JSONObject json = new JSONObject(sb.toString());
			result = util.jsonToSmsSingleVoiceSenderResult(json);
		} else
		{
			result = new SmsSingleVoiceSenderResult();
			result.result = httpRspCode;
			result.errmsg = (new StringBuilder("http error ")).append(httpRspCode).append(" ").append(conn.getResponseMessage()).toString();
		}
		return result;
	}
}
