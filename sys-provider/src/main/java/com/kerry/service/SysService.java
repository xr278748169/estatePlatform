package com.kerry.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.system.model.SysModel;
import com.kerry.system.inter.ISysInter;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 应用管理
 * Created by wangshen on 2017/4/10.
 */
@Service
public class SysService implements ISysInter{

    @Autowired
    private SQLManager sqlManager;


    /**
     * 保存
     * @param sysModel
     * @return
     */
    @Override
    public String insert(SysModel sysModel) throws Exception {
        int num = sqlManager.insert(sysModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 更新
     * @param sysModel
     * @return
     */
    @Override
    public String update(SysModel sysModel) throws Exception {
        int num = sqlManager.updateTemplateById(sysModel);
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
        int num = sqlManager.deleteById(SysModel.class,id);
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
    public SysModel selectById(String id) throws Exception {
        return sqlManager.unique(SysModel.class,id);
    }

    /**
     * 查询
     * @param params
     * @return
     */
    @Override
    public PageQuery<SysModel> findByPage(SearchParams params) throws Exception {
        PageQuery<SysModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("sysModel.query",SysModel.class,query);
        return query;
    }
}
