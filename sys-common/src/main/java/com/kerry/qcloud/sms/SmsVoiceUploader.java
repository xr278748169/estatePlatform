// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SmsVoiceUploader.java

package com.kerry.qcloud.sms;

import java.io.*;
import org.apache.http.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

// Referenced classes of package com.qcloud.sms:
//			SmsSenderUtil, SmsVoiceUploaderResult

public class SmsVoiceUploader
{

	String appkey;
	int appid;
	String url;
	SmsSenderUtil util;

	public SmsVoiceUploader(int appid, String appkey)
	{
		url = "https://test.tim.qq.com/v3/tlsvoicesvr/upload_voice";
		util = new SmsSenderUtil();
		this.appid = appid;
		this.appkey = appkey;
	}

	public SmsVoiceUploaderResult upload(String filePath)
		throws Exception
	{
		CloseableHttpClient httpClient;
		HttpPost httpPost;
		String wholeUrl = String.format("%s?sdkappid=%d", new Object[] {
			url, Integer.valueOf(appid)
		});
		String random = (new StringBuilder()).append(util.getRandom()).toString();
		String curTime = (new StringBuilder()).append(System.currentTimeMillis() / 1000L).toString();
		File voiceFile = new File(filePath);
		httpClient = HttpClients.createDefault();
		httpPost = new HttpPost(wholeUrl);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		ContentType contentType = ContentType.create((new StringBuilder("application/octet-stream;\r\nContent-Length: ")).append(voiceFile.length()).toString());
		builder.addBinaryBody("file", voiceFile, contentType, voiceFile.getName());
		builder.addTextBody("sig", util.strToHash(String.format("appkey=%s&rand=%s&time=%s", new Object[] {
			appkey, random, curTime
		})));
		builder.addTextBody("rand", random);
		builder.addTextBody("time", curTime);
		HttpEntity multipart = builder.build();
		httpPost.setEntity(multipart);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		multipart.writeTo(bos);
		System.out.println(bos.toString());
		SmsVoiceUploaderResult result;
		HttpResponse response = httpClient.execute(httpPost);
		int httpRspCode = response.getStatusLine().getStatusCode();
		if (200 == httpRspCode)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = br.readLine()) != null) 
				sb.append(line);
			JSONObject json = new JSONObject(sb.toString());
			result = util.jsonToSmsVoiceUploaderResult(json);
			System.out.println(sb.toString());
		} else
		{
			result = new SmsVoiceUploaderResult();
			result.result = -1;
			result.msg = (new StringBuilder("http error ")).append(httpRspCode).toString();
		}
		httpClient.close();
		httpClient.close();
		return result;
	}
}
