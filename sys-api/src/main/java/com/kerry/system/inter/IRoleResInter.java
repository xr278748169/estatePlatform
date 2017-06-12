package com.kerry.system.inter;

import com.alibaba.fastjson.JSONObject;
import com.kerry.system.inter.base.BaseInter;
import com.kerry.system.model.RoleResModel;

import java.util.List;

/**
 * Created by wangshen on 2017/4/12.
 */
public interface IRoleResInter extends BaseInter<RoleResModel> {

    List<RoleResModel> findByRoleId(String roleId,String isRoot);

    String insertBatch(JSONObject jsonObject);

    int deleteByRoleId(String roleId);
}
