package com.kerry.estate.base.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.IPropertyCoInter;
import com.kerry.estate.base.model.PropertyCoModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 物业公司管理
 * Created by wangshen on 2017/7/3.
 */
@Service
@Transactional("txManager")
public class PropertyCoService implements IPropertyCoInter {


    @Autowired
    private SQLManager sqlManager;

    /**
     * 保存
     * @param propertyCoModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(PropertyCoModel propertyCoModel) throws Exception {
        propertyCoModel.setCreateDate(new Date());
        int num = sqlManager.insert(propertyCoModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 更新
     * @param propertyCoModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(PropertyCoModel propertyCoModel) throws Exception {
        propertyCoModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(propertyCoModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 主键删除
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public String delete(String id) throws Exception {
        int num = sqlManager.deleteById(PropertyCoModel.class, id);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 组件查询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public PropertyCoModel selectById(String id) throws Exception {
        return sqlManager.unique(PropertyCoModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<PropertyCoModel> findByPage(SearchParams params) throws Exception {
        PageQuery<PropertyCoModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("propertyCoModel.query",PropertyCoModel.class,query);
        return query;
    }

    /**
     * 自定义条件查询
     * @param params
     * @return
     */
    @Override
    public List<PropertyCoModel> findByCondition(PropertyCoModel params) throws Exception {
        return sqlManager.template(params);
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<PropertyCoModel> findAll() throws Exception {
        return sqlManager.all(PropertyCoModel.class);
    }
}
