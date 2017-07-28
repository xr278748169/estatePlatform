package com.kerry.estate.serv.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.IResFileInter;
import com.kerry.estate.base.model.ResFileModel;
import com.kerry.estate.serv.dao.SuitDao;
import com.kerry.estate.serv.inter.ISuitInter;
import com.kerry.estate.serv.model.ReplyModel;
import com.kerry.estate.serv.model.SuitModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 投诉建议
 * Created by wangshen on 2017/7/27.
 */
@Service
@Transactional("txManager")
public class SuitService implements ISuitInter {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private SuitDao suitDao;

    @Autowired
    private IResFileInter resFileInter;

    /**
     * 保存
     * @param suitModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(SuitModel suitModel) throws Exception {
        suitModel.setCreateDate(new Date());
        int num = sqlManager.insert(suitModel);
        if(num > 0){
            if(suitModel.getResFileList()!=null && suitModel.getResFileList().size()>0){
                resFileInter.insertBatch(suitModel.getResFileList());
            }
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param suitModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(SuitModel suitModel) throws Exception {
        suitModel.setCreateDate(new Date());
        int num = sqlManager.updateTemplateById(suitModel);
        if(num > 0){
            if(suitModel.getResFileList()!=null && suitModel.getResFileList().size()>0){
                resFileInter.insertBatch(suitModel.getResFileList());
            }
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
        int num = sqlManager.deleteById(SuitModel.class, id);
        if(num > 0){
            resFileInter.deleteByBussId(id);
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
    public SuitModel selectById(String id) throws Exception {
        SuitModel suitModel = suitDao.selectById(id);
        //查询资源文件
        ResFileModel params = new ResFileModel();
        params.setBussId(suitModel.getStId());
        suitModel.setResFileList(resFileInter.findByCondition(params));
        return suitModel;
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<SuitModel> findByPage(SearchParams params) throws Exception {
        PageQuery<SuitModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("suitModel.query",SuitModel.class,query);
        return query;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<SuitModel> findByCondition(SuitModel params) throws Exception {
        return sqlManager.template(params);
    }
}
