package com.kerry.estate.base.inter;

import com.alibaba.fastjson.JSONObject;
import com.kerry.estate.BaseInter;
import com.kerry.estate.base.model.BuildingModel;

import java.util.List;

/**
 * 小区楼宇管理
 * Created by wangshen on 2017/7/3.
 */
public interface IBuildingInter extends BaseInter<BuildingModel> {

    List<JSONObject> findAllToJson(String comId) throws Exception;
}
