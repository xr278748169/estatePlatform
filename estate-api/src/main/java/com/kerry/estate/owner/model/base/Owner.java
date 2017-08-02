package com.kerry.estate.owner.model.base;

import org.beetl.sql.core.annotatoin.AssignID;

import java.util.Date;

public class Owner {

    private String ownId;

    private String ownType;

    private String ownName;

    private String idNumber;

    private String telephone;

    private String comId;

    private String budId;

    private String burId;

    private String tuId;

    private String ownState;

    private String authState;

    private String visitCode;

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
    public String getOwnId() {
        return ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
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

    public String getOwnState() {
        return ownState;
    }

    public void setOwnState(String ownState) {
        this.ownState = ownState;
    }

    public String getAuthState() {
        return authState;
    }

    public void setAuthState(String authState) {
        this.authState = authState;
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
