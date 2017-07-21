package com.kerry.estate.owner.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.owner.inter.IOwnerInter;
import com.kerry.estate.owner.model.OwnerModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业主信息管理
 * Created by wangshen on 2017/7/20.
 */
@Service
@Transactional("txManager")
public class OwnerService implements IOwnerInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 保存
     * @param ownerModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(OwnerModel ownerModel) throws Exception {
        int num = sqlManager.insert(ownerModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param ownerModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(OwnerModel ownerModel) throws Exception {
        int num = sqlManager.updateTemplateById(ownerModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public String delete(String id) throws Exception {
        int num = sqlManager.deleteById(OwnerModel.class ,id);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public OwnerModel selectById(String id) throws Exception {
        return sqlManager.unique(OwnerModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<OwnerModel> findByPage(SearchParams params) throws Exception {
        PageQuery<OwnerModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("ownerModel.query",OwnerModel.class,query);
        return query;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<OwnerModel> findByCondition(OwnerModel params) throws Exception {
        return sqlManager.template(params);
    }
}
