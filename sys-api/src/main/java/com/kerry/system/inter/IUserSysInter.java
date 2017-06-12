package com.kerry.system.inter;

import com.alibaba.fastjson.JSONObject;
import com.kerry.system.inter.base.BaseInter;
import com.kerry.system.model.UserSysModel;

import java.util.List;

/**
 * Created by wangshen on 2017/6/9.
 */
public interface IUserSysInter extends BaseInter<UserSysModel> {

    /**
     * 根据userId查询
     * @param userId
     * @return
     * @throws Exception
     */
    List<UserSysModel> findByUserId(String userId) throws Exception;

    /**
     * 批量保存
     * @param jsonObject
     * @return
     * @throws Exception
     */
    String insertBatch(JSONObject jsonObject) throws Exception;

}
