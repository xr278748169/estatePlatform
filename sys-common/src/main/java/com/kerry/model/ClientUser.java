package com.kerry.model;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshen on 2017/5/25.
 */
public class ClientUser {

    private String clientId;

    private String clientType;

    private String sign;

    private String accessToken;

    private String code;

    private String userName;

    private String userType;

    private Map<String,Object> userRes;

    private Map<String,Object> userRole;

    private Map<String,Object> userSys;

    private String orgId;

    private String authCode;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Map<String, Object> getUserRes() {
        return userRes;
    }

    public void setUserRes(Map<String, Object> userRes) {
        this.userRes = userRes;
    }

    public Map<String, Object> getUserRole() {
        return userRole;
    }

    public void setUserRole(Map<String, Object> userRole) {
        this.userRole = userRole;
    }

    public Map<String, Object> getUserSys() {
        return userSys;
    }

    public void setUserSys(Map<String, Object> userSys) {
        this.userSys = userSys;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
