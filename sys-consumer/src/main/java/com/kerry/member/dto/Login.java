package com.kerry.member.dto;

/**
 * Created by wangshen on 2017/5/19.
 */
public class Login {

    private String userName;

    private String password;

    private String slatCode;

    private String accessToken;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSlatCode() {
        return slatCode;
    }

    public void setSlatCode(String slatCode) {
        this.slatCode = slatCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
