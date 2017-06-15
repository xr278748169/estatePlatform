package com.kerry.system.model;

import com.kerry.system.model.base.UserSys;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * Created by wangshen on 2017/6/9.
 */
@Table(name = "p_user_sys")
public class UserSysModel extends UserSys implements Serializable {

    private String sysUrl;

    private String sysName;

    public String getSysUrl() {
        return sysUrl;
    }

    public void setSysUrl(String sysUrl) {
        this.sysUrl = sysUrl;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
}
