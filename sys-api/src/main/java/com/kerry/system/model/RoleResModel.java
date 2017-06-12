package com.kerry.system.model;

import com.kerry.system.model.base.RoleRes;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * Created by wangshen on 2017/4/10.
 */
@Table(name = "p_role_res")
public class RoleResModel extends RoleRes implements Serializable {

    private String resName;

    private String resUrl;

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }
}
