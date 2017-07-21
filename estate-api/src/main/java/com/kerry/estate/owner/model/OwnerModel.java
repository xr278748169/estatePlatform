package com.kerry.estate.owner.model;

import com.kerry.estate.owner.model.base.Owner;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

/**
 * 业主信息
 * Created by wangshen on 2017/7/20.
 */
@Table(name = "e_owner")
public class OwnerModel extends Owner implements Serializable {

    private String comName;

    private String budName;

    private String cellName;

    private String floor;

    private String roomName;

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

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
