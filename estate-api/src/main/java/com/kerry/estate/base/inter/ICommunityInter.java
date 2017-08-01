package com.kerry.estate.base.inter;

import com.alibaba.fastjson.JSONObject;
import com.kerry.estate.BaseInter;
import com.kerry.estate.base.model.CommunityModel;

import java.util.List;

/**
 * 小区管理
 * Created by wangshen on 2017/7/3.
 */
public interface ICommunityInter extends BaseInter<CommunityModel> {

    List<CommunityModel> findAll() throws Exception;

    List<JSONObject> findAllToJson() throws Exception;
}
