package com.kerry.estate.base.model;

import com.kerry.estate.base.model.base.Community;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 小区管理
 * Created by wangshen on 2017/7/3.
 */
@Table(name = "e_community")
public class CommunityModel extends Community implements Serializable {

    private String pcName;

    private int ownNum;//业主数量

    private int ownAuthNum;//认证数量

    private int applyAuthNum;//申请认证数量

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }

    public int getOwnNum() {
        return ownNum;
    }

    public void setOwnNum(int ownNum) {
        this.ownNum = ownNum;
    }

    public int getOwnAuthNum() {
        return ownAuthNum;
    }

    public void setOwnAuthNum(int ownAuthNum) {
        this.ownAuthNum = ownAuthNum;
    }

    public int getApplyAuthNum() {
        return applyAuthNum;
    }

    public void setApplyAuthNum(int applyAuthNum) {
        this.applyAuthNum = applyAuthNum;
    }
}
