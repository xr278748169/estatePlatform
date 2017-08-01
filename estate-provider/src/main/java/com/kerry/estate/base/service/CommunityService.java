package com.kerry.estate.base.service;

import com.alibaba.fastjson.JSONObject;
import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.ICommunityInter;
import com.kerry.estate.base.model.CommunityModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 小区信息管理
 * Created by wangshen on 2017/7/4.
 */
@Service
@Transactional("txManager")
public class CommunityService implements ICommunityInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 保存
     * @param communityModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(CommunityModel communityModel) throws Exception {
        communityModel.setCreateDate(new Date());
        int num = sqlManager.insert(communityModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param communityModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(CommunityModel communityModel) throws Exception {
        communityModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(communityModel);
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
        int num = sqlManager.deleteById(CommunityModel.class, id);
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
    public CommunityModel selectById(String id) throws Exception {
        return sqlManager.unique(CommunityModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<CommunityModel> findByPage(SearchParams params) throws Exception {
        PageQuery<CommunityModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("communityModel.query",CommunityModel.class,query);
        return query;
    }

    /**
     * 自定义条件查询
     * @param params
     * @return
     */
    @Override
    public List<CommunityModel> findByCondition(CommunityModel params) throws Exception {
        return sqlManager.template(params);
    }

    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    @Override
    public List<CommunityModel> findAll() throws Exception {
        return sqlManager.all(CommunityModel.class);
    }

    /**
     * 查询全部并且转为options选择需要的数据
     * @return
     * @throws Exception
     */
    @Override
    public List<JSONObject> findAllToJson() throws Exception {
        List<JSONObject> result = new ArrayList<>();
        List<CommunityModel> comList = sqlManager.all(CommunityModel.class);
        for (CommunityModel com : comList) {
            JSONObject comJson = new JSONObject();
            comJson.put("value", com.getComId());
            comJson.put("label", com.getComName());
            result.add(comJson);
        }
        return result;
    }

}
