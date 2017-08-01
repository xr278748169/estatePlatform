package com.kerry.estate.base.inter;

import com.alibaba.fastjson.JSONObject;
import com.kerry.estate.BaseInter;
import com.kerry.estate.base.model.BuildingRoomModel;

import java.util.List;

/**
 * 楼宇房号管理
 * Created by wangshen on 2017/7/3.
 */
public interface IBuildingRoomInter extends BaseInter<BuildingRoomModel> {

    /**
     * 房号批量保存
     * @param brList
     */
    void insertBatch(List<BuildingRoomModel> brList) throws Exception;

    List<JSONObject> findByConditionToJson(BuildingRoomModel params) throws Exception;
}
