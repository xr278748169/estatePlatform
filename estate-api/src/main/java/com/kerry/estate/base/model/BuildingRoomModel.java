package com.kerry.estate.base.model;

import com.kerry.estate.base.model.base.BuildingRoom;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 小区楼宇房间管理
 * Created by wangshen on 2017/7/3.
 */
@Table(name = "e_building_room")
public class BuildingRoomModel extends BuildingRoom implements Serializable {

    private String comName;

    private String budName;

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getBudName() {
        return budName;
    }

    public void setBudName(String budName) {
        this.budName = budName;
    }
}
