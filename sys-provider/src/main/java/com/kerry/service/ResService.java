package com.kerry.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.system.model.ResModel;
import com.kerry.system.inter.IResInter;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshen on 2017/4/12.
 */
@Service
@Transactional
public class ResService implements IResInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 保存
     * @param resModel
     * @return
     */
    @Override
    public String insert(ResModel resModel) throws Exception {
        int num = sqlManager.insert(resModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 更新
     * @param resModel
     * @return
     */
    @Override
    public String update(ResModel resModel) throws Exception {
        int num = sqlManager.updateTemplateById(resModel);
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
        //判断是否存在下级
        ResModel params = new ResModel();
        params.setParentResId(id);
        List<ResModel> resList = sqlManager.template(params);
        if(resList.size() > 0){
            return ResponseEntity.createErrorJsonResponse(Constant.DTAT_RESULT_SUB);
        }
        int num = sqlManager.deleteById(ResModel.class, id);
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
    public ResModel selectById(String id) throws Exception {
        return sqlManager.unique(ResModel.class, id);
    }

    /**
     * 分页
     * @param params
     * @return
     */
    @Override
    public PageQuery<ResModel> findByPage(SearchParams params) throws Exception {
        PageQuery<ResModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("resModel.query",ResModel.class,query);
        return query;
    }

    /**
     * 按级别查询资源列表
     * @param parentId
     * @return
     */
    @Override
    public List<ResModel> findByParentId(String parentId) throws Exception {
        Map<String,Object> params = new HashMap<>();
        if(parentId.equals("0")){
            parentId = "";
        }
        params.put("parentId", parentId);
        return sqlManager.select("resModel.querySubRes", ResModel.class, params);
    }
}
