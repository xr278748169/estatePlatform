package com.kerry.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 数据响应工具
 * Created by wangshen on 2017/4/10.
 */
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = 4281230659824659225L;

    public static final int SUCCEED = 0;//成功
    public static final int FAIL = 1;//失败
    public static final int NULL = 2;//空
    public static final int DUB = 4;//重复
    public static final int TEST_ERROR = 3;
    private int code;
    private T responseData;

    public ResponseEntity(int code, T responseData) {
        this.code = code;
        this.responseData = responseData;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public static final String createTestErrorJsonResponse(String msg) {
        return JSONObject.toJSON(new ResponseEntity<String>(ResponseEntity.TEST_ERROR, msg)).toString();
    }

    public static final String createNULLJsonResponse(String msg) {
        return JSONObject.toJSON(new ResponseEntity<String>(ResponseEntity.NULL, msg)).toString();
    }

    public static final String createErrorJsonResponse(String msg) {
        return JSONObject.toJSON(new ResponseEntity<String>(ResponseEntity.FAIL, msg)).toString();
    }

    public static final String createDuplicationJsonResponse(String msg) {
        return JSONObject.toJSON(new ResponseEntity<String>(ResponseEntity.DUB, msg)).toString();
    }

    public static final String createNormalJsonResponse(String msg) {
        return JSONObject.toJSON(new ResponseEntity<String>(ResponseEntity.SUCCEED, msg)).toString();
    }

    public static final String createNormalJsonResponse(JSONObject jsonObject) {
        return JSONObject.toJSON(new ResponseEntity<JSONObject>(ResponseEntity.SUCCEED, jsonObject)).toString();
    }

    public static final String createNormalJsonResponse(JSONArray jsonArray) {
        return JSONObject.toJSON(new ResponseEntity<JSONArray>(ResponseEntity.SUCCEED, jsonArray)).toString();
    }
}
