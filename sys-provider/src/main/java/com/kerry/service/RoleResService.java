package com.kerry.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.dao.RoleResDao;
import com.kerry.system.inter.IRoleResInter;
import com.kerry.system.model.RoleResModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色资源设置
 * Created by wangshen on 2017/4/12.
 */
@Service
public class RoleResService implements IRoleResInter {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private RoleResDao roleResDao;

    @Override
    public String insert(RoleResModel roleResModel) {
        return null;
    }

    @Override
    public String update(RoleResModel roleResModel) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }


    @Override
    public RoleResModel selectById(String id) {
        return null;
    }

    @Override
    public PageQuery<RoleResModel> findByPage(SearchParams params) {
        return null;
    }

    /**
     * 根据角色编号查询资源
     * @param roleId
     * @return
     */
    @Override
    public List<RoleResModel> findByRoleId(String roleId,String isRoot) {

        System.out.println("测试信息："+isRoot);

        if(isRoot.equals("no")){
            isRoot = "";
        }
        return roleResDao.findByRoleId(roleId,isRoot);
    }

    /**
     * 批量插入角色资源
     * @param jsonObject
     */
    @Override
    public String insertBatch(JSONObject jsonObject) {
        List<RoleResModel> roleResList = new ArrayList<>();
        String roleId = jsonObject.getString("roleId");
        JSONArray jsonArray = jsonObject.getJSONArray("resList");
        Map<String,Object> resMap = new HashMap<>();
        for (Object object : jsonArray){
            JSONObject resObj = JSONObject.parseObject(JSON.toJSONString(object));
            resMap.put(resObj.getString("resId"),resObj);
        }
        for (Object object : jsonArray){
            JSONObject resObj = JSONObject.parseObject(JSON.toJSONString(object));
            //判断一下查询未全选节点的父级节点
            if(resObj.getString("parentResId")!=null&&!resObj.getString("parentResId").equals("")){
                if(!resMap.containsKey(resObj.getString("parentResId"))){
                    RoleResModel roleRes = new RoleResModel();
                    roleRes.setRoleId(roleId);
                    roleRes.setResId(resObj.getString("parentResId"));
                    roleRes.setExt2("root");
                    roleResList.add(roleRes);
                    resMap.put(resObj.getString("parentResId"),"root");
                }
            }
            RoleResModel roleRes = new RoleResModel();
            roleRes.setRoleId(roleId);
            roleRes.setResId(resObj.getString("resId"));
            roleRes.setExt1(resObj.getString("resName"));
            roleResList.add(roleRes);
        }
        if(roleResList.size()!=0){
            this.deleteByRoleId(roleId);
            sqlManager.insertBatch(RoleResModel.class,roleResList);
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 根据角色编号删除资源
     * @param roleId
     * @return
     */
    @Override
    public int deleteByRoleId(String roleId) {
        return roleResDao.deleteByRoleId(roleId);
    }
}
