package com.kerry.estate.msg.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.msg.inter.IEssayInter;
import com.kerry.estate.msg.model.EssayModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 物业新闻消息
 * Created by wangshen on 2017/7/24.
 */
@Service
@Transactional("txManager")
public class EssayService implements IEssayInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 保存
     * @param essayModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(EssayModel essayModel) throws Exception {
        essayModel.setCreateDate(new Date());
        int num = sqlManager.insert(essayModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param essayModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(EssayModel essayModel) throws Exception {
        essayModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(essayModel);
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
        int num = sqlManager.deleteById(EssayModel.class, id);
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
    public EssayModel selectById(String id) throws Exception {
        return sqlManager.unique(EssayModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<EssayModel> findByPage(SearchParams params) throws Exception {
        PageQuery<EssayModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("essayModel.query",EssayModel.class,query);
        return query;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<EssayModel> findByCondition(EssayModel params) throws Exception {
        return sqlManager.template(params);
    }
}
