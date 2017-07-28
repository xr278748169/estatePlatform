package com.kerry.estate.serv.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.serv.inter.IReplyInter;
import com.kerry.estate.serv.model.ReplyModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 回复消息管理
 * Created by wangshen on 2017/7/27.
 */
@Service
@Transactional("txManager")
public class ReplyService implements IReplyInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 保存
     * @param replyModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(ReplyModel replyModel) throws Exception {
        replyModel.setCreateDate(new Date());
        replyModel.setReDate(new Date());
        int num = sqlManager.insert(replyModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param replyModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(ReplyModel replyModel) throws Exception {
        replyModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(replyModel);
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
        int num = sqlManager.deleteById(ReplyModel.class, id);
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
    public ReplyModel selectById(String id) throws Exception {
        return sqlManager.unique(ReplyModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<ReplyModel> findByPage(SearchParams params) throws Exception {
        return null;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<ReplyModel> findByCondition(ReplyModel params) throws Exception {
        return sqlManager.template(params);
    }
}
