package com.kerry.system.model.base;

import org.beetl.sql.core.annotatoin.AssignID;

public class Region {
    private String regionId;

    private String regionCode;

    private String regionName;

    private String parentId;

    private String regionLevel;

    private String regionOrder;

    private String regionNameEn;

    private String regionShortnameEn;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId == null ? null : regionId.trim();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(String regionLevel) {
        this.regionLevel = regionLevel == null ? null : regionLevel.trim();
    }

    public String getRegionOrder() {
        return regionOrder;
    }

    public void setRegionOrder(String regionOrder) {
        this.regionOrder = regionOrder == null ? null : regionOrder.trim();
    }

    public String getRegionNameEn() {
        return regionNameEn;
    }

    public void setRegionNameEn(String regionNameEn) {
        this.regionNameEn = regionNameEn == null ? null : regionNameEn.trim();
    }

    public String getRegionShortnameEn() {
        return regionShortnameEn;
    }

    public void setRegionShortnameEn(String regionShortnameEn) {
        this.regionShortnameEn = regionShortnameEn == null ? null : regionShortnameEn.trim();
    }
}