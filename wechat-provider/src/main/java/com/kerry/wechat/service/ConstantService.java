package com.kerry.wechat.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.wechat.inter.IConstantInter;
import com.kerry.wechat.model.ConstantModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 微信配置信息管理
 * Created by wangshen on 2017/6/21.
 */
@Service
@Transactional("txManager")
public class ConstantService implements IConstantInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 配置新增
     * @param constantModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(ConstantModel constantModel) throws Exception {
        constantModel.setCreateDate(new Date());
        int num = sqlManager.insert(constantModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 配置修改
     * @param constantModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(ConstantModel constantModel) throws Exception {
        constantModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(constantModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 配置删除
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public String delete(String id) throws Exception {
        int num = sqlManager.deleteById(ConstantModel.class, id);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 配置主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public ConstantModel selectById(String id) throws Exception {
        return sqlManager.unique(ConstantModel.class, id);
    }

    /**
     * 配置分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<ConstantModel> findByPage(SearchParams params) throws Exception {
        PageQuery<ConstantModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("constantModel.query",ConstantModel.class,query);
        return query;
    }
}
