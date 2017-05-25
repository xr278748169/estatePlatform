package com.kerry.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.system.model.UserRoleModel;
import com.kerry.system.inter.IUserRoleInter;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangshen on 2017/4/12.
 */
@Service
public class UserRoleService implements IUserRoleInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 用户角色新增
     * @param userRoleModel
     * @return
     */
    @Override
    public String insert(UserRoleModel userRoleModel) throws Exception {
        int num = sqlManager.insert(userRoleModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 用户角色修改
     * @param userRoleModel
     * @return
     */
    @Override
    public String update(UserRoleModel userRoleModel) throws Exception {
        int num = sqlManager.updateTemplateById(userRoleModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 用户角色删除
     * @param id
     * @return
     */
    @Override
    public String delete(String id) throws Exception {
        int num = sqlManager.deleteById(UserRoleModel.class,id);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 根据主键查询用户角色
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public UserRoleModel selectById(String id) throws Exception {
        return sqlManager.unique(UserRoleModel.class,id);
    }

    @Override
    public PageQuery<UserRoleModel> findByPage(SearchParams params) throws Exception {
        return null;
    }

    /**
     * 角色查询
     * @param params
     * @return
     */
    @Override
    public List<UserRoleModel> select(UserRoleModel params) throws Exception {
        return sqlManager.template(params);
    }
}
