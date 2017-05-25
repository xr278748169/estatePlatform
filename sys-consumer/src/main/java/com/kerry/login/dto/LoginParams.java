package com.kerry.login.dto;

/**
 * Created by wangshen on 2017/5/24.
 */
public class LoginParams {

    private String userName;

    private String password;

    //建立连接的地址校验信息
    private String sercret;

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

    public String getSercret() {
        return sercret;
    }

    public void setSercret(String sercret) {
        this.sercret = sercret;
    }
}
