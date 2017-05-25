package com.kerry.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IRoleInter;
import com.kerry.system.model.RoleModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色管理
 * Created by wangshen on 2017/4/12.
 */
@Service
@Transactional
public class RoleService implements IRoleInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 保存
     * @param roleModel
     * @return
     */
    @Override
    public String insert(RoleModel roleModel) throws Exception {
        int num = sqlManager.insert(roleModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 更新
     * @param roleModel
     * @return
     */
    @Override
    public String update(RoleModel roleModel) throws Exception {
        int num = sqlManager.updateTemplateById(roleModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public String delete(String id) throws Exception {
        int num = sqlManager.deleteById(RoleModel.class, id);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @Override
    public RoleModel selectById(String id) throws Exception {
        return sqlManager.unique(RoleModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageQuery<RoleModel> findByPage(SearchParams params) throws Exception {
        PageQuery<RoleModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("roleModel.query",RoleModel.class,query);
        return query;
    }
}
