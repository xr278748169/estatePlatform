package com.kerry.estate.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


/**
 * 移动端业主认证传输对象
 * Created by wangshen on 2017/8/1.
 */
public class AuthDto {

    private String comId;

    private String budId;

    private String burId;

    private String tuId;//微信用户表示

    @NotNull(message = "请选择业主类型")
    @NotBlank(message = "请选择业主类型")
    private String ownType;

    @NotNull(message = "请输入业主姓名")
    @NotBlank(message = "请输入业主姓名")
    private String ownName;

    @NotNull(message = "请输入身份证号码")
    @NotBlank(message = "请输入身份证号码")
    private String idNumber;

    @NotNull(message = "请输入联系电话")
    @NotBlank(message = "请输入联系电话")
    private String telephone;

    private String visitCode;

    @NotNull(message = "请输入短信验证码")
    @NotBlank(message = "请输入短信验证码")
    private String authCode;

    public String getComId() {
        return comId;
    }

    public void setComId(String comId) {
        this.comId = comId;
    }

    public String getBudId() {
        return budId;
    }

    public void setBudId(String budId) {
        this.budId = budId;
    }

    public String getBurId() {
        return burId;
    }

    public void setBurId(String burId) {
        this.burId = burId;
    }

    public String getTuId() {
        return tuId;
    }

    public void setTuId(String tuId) {
        this.tuId = tuId;
    }

    public String getOwnType() {
        return ownType;
    }

    public void setOwnType(String ownType) {
        this.ownType = ownType;
    }

    public String getOwnName() {
        return ownName;
    }

    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVisitCode() {
        return visitCode;
    }

    public void setVisitCode(String visitCode) {
        this.visitCode = visitCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "AuthDto{" +
                "comId='" + comId + '\'' +
                ", budId='" + budId + '\'' +
                ", burId='" + burId + '\'' +
                ", tuId='" + tuId + '\'' +
                ", ownType='" + ownType + '\'' +
                ", ownName='" + ownName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", telephone='" + telephone + '\'' +
                ", visitCode='" + visitCode + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }
}
