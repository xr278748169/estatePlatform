package com.kerry.estate.serv.model;

import com.kerry.estate.base.model.ResFileModel;
import com.kerry.estate.base.model.base.ResFile;
import com.kerry.estate.serv.model.base.Repairs;
import com.kerry.utils.DateUtils;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.List;

/**
 * 报修管理
 * Created by wangshen on 2017/7/27.
 */
@Table(name = "e_repairs")
public class RepairsModel extends Repairs implements Serializable {

    private String formatReDate;

    public String getFormatReDate() {
        if(getReDate() != null){
            return DateUtils.getDate(getReDate(),"yyyy-MM-dd HH:mm:ss");
        }
        return null;
    }

    //=====关联字段=====//
    private List<ResFileModel> resFileList;

    private String comName;

    private String budName;

    private String cellName;

    private String floor;

    private String roomName;

    private String ownName;

    private String telephone;

    private String idNumber;

    public List<ResFileModel> getResFileList() {
        return resFileList;
    }

    public void setResFileList(List<ResFileModel> resFileList) {
        this.resFileList = resFileList;
    }

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

    public String getOwnName() {
        return ownName;
    }

    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
