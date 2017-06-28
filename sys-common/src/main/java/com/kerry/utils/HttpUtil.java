package com.kerry.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * http请求工具
 * Created by wangshen on 2017/6/26.
 */
public class HttpUtil {

    public static final String METHOD_TYPE_GET = "GET";
    public static final String METHOD_TYPE_POST = "POST";

    public static String httpRequest(String urlPath, String methodType, String jsonStr) {
        StringBuffer stringBuffer = null;
        BufferedReader in = null;
        // 1.数据校验
        if (urlPath == null || methodType == null) {
            return null;
        }
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(methodType);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setDoOutput(true);
            // 建立实际的连接
            connection.connect();
			/*
			 * ===================获取所有响应头字段============================
			 * Map<String, List<String>> map = connection.getHeaderFields();
			 * 遍历所有的响应头字段 for (String key : map.keySet()) {
			 * System.out.println(key + "--->" + map.get(key)); }
			 */
            if (METHOD_TYPE_POST.equals(methodType) && jsonStr != null) {
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(jsonStr);
                out.flush();
                out.close();
            }
            // 定义 BufferedReader输入流来读取URL的响应
            stringBuffer = new StringBuffer();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

}
