package com.kerry.estate.serv.model.base;


import org.beetl.sql.core.annotatoin.AssignID;

import java.util.Date;

public class Reply {

    private String reId;

    private String bussType;

    private String bussId;

    private String reUser;

    private Date reDate;

    private String reContent;

    private String authCode;

    private String remarks;

    private String ext1;

    private String ext2;

    private String ext3;

    private String createUser;

    private Date createDate;

    private String updateUser;

    private Date updateDate;

    @AssignID("pkId")
    public String getReId() {
        return reId;
    }

    public void setReId(String reId) {
        this.reId = reId;
    }

    public String getBussType() {
        return bussType;
    }

    public void setBussType(String bussType) {
        this.bussType = bussType;
    }

    public String getBussId() {
        return bussId;
    }

    public void setBussId(String bussId) {
        this.bussId = bussId;
    }

    public String getReUser() {
        return reUser;
    }

    public void setReUser(String reUser) {
        this.reUser = reUser;
    }

    public Date getReDate() {
        return reDate;
    }

    public void setReDate(Date reDate) {
        this.reDate = reDate;
    }

    public String getReContent() {
        return reContent;
    }

    public void setReContent(String reContent) {
        this.reContent = reContent;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
