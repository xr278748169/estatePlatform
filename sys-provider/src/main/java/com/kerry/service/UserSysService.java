package com.kerry.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.dao.UserSysDao;
import com.kerry.system.inter.IUserSysInter;
import com.kerry.system.model.UserSysModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户访问模块管理
 * Created by wangshen on 2017/6/9.
 */
@Service
@Transactional("txManager")
public class UserSysService implements IUserSysInter {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private UserSysDao userSysDao;

    @Override
    public String insert(UserSysModel userSysModel) throws Exception {
        return null;
    }

    @Override
    public String update(UserSysModel userSysModel) throws Exception {
        return null;
    }

    @Override
    public String delete(String id) throws Exception {
        return null;
    }

    @Override
    public UserSysModel selectById(String id) throws Exception {
        return null;
    }

    @Override
    public PageQuery<UserSysModel> findByPage(SearchParams params) throws Exception {
        return null;
    }

    /**
     * 查询用户可访问模块
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public List<UserSysModel> findByUserId(String userId) throws Exception {
        return userSysDao.findByUserId(userId);
    }

    /**
     * 批量插入可访问模块
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @Override
    public String insertBatch(JSONObject jsonObject) throws Exception {
        List<UserSysModel> userSysList = new ArrayList<>();
        String userId = jsonObject.getString("userId");
        JSONArray jsonArray = jsonObject.getJSONArray("sysList");
        for(Object object : jsonArray){
            JSONObject sysObj = JSONObject.parseObject(JSON.toJSONString(object));
            UserSysModel userSys = new UserSysModel();
            userSys.setUserId(userId);
            userSys.setSysId(sysObj.getString("sysId"));
            userSysList.add(userSys);
        }
        if(userSysList.size()!=0){
            userSysDao.deleteByUserId(userId);
            sqlManager.insertBatch(UserSysModel.class,userSysList);
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }
}
