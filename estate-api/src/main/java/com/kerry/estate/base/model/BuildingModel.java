package com.kerry.estate.base.model;

import com.kerry.estate.base.model.base.Building;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 小区楼宇
 * Created by wangshen on 2017/7/3.
 */
@Table(name = "e_building")
public class BuildingModel extends Building implements Serializable {

    private String comName;

    private String floorHouseholds;

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getFloorHouseholds() {
        return floorHouseholds;
    }

    public void setFloorHouseholds(String floorHouseholds) {
        this.floorHouseholds = floorHouseholds;
    }
}
