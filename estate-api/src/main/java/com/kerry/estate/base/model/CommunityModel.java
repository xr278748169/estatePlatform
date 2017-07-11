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

    public String getPcName() {
        return pcName;
    }

    public void setPcName(String pcName) {
        this.pcName = pcName;
    }
}
