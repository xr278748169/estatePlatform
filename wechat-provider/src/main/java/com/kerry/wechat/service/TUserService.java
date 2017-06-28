package com.kerry.wechat.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.wechat.inter.ITUserInter;
import com.kerry.wechat.model.TUserModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 微信关注用户
 * Created by wangshen on 2017/6/26.
 */
@Service
@Transactional("txManager")
public class TUserService implements ITUserInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 新增
     * @param tUserModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(TUserModel tUserModel) throws Exception {
        tUserModel.setCreateDate(new Date());
        int num = sqlManager.insert(tUserModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param tUserModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(TUserModel tUserModel) throws Exception {
        tUserModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(tUserModel);
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
        int num = sqlManager.deleteById(TUserModel.class, id);
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
    public TUserModel selectById(String id) throws Exception {
        return sqlManager.unique(TUserModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<TUserModel> findByPage(SearchParams params) throws Exception {
        PageQuery<TUserModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("tUserModel.query",TUserModel.class,query);
        return query;
    }
}
