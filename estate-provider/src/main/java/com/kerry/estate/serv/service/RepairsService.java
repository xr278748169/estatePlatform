package com.kerry.estate.serv.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.IResFileInter;
import com.kerry.estate.base.model.ResFileModel;
import com.kerry.estate.serv.dao.RepairsDao;
import com.kerry.estate.serv.inter.IRepairsInter;
import com.kerry.estate.serv.model.RepairsModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 报修管理
 * Created by wangshen on 2017/7/27.
 */
@Service
@Transactional("txManager")
public class RepairsService implements IRepairsInter {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private RepairsDao repairsDao;

    @Autowired
    private IResFileInter resFileInter;

    /**
     * 保存
     * @param repairsModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(RepairsModel repairsModel) throws Exception {
        repairsModel.setCreateDate(new Date());
        int num = sqlManager.insert(repairsModel);
        if(num > 0){
            if(repairsModel.getResFileList()!=null && repairsModel.getResFileList().size()>0){
                resFileInter.insertBatch(repairsModel.getResFileList());
            }
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param repairsModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(RepairsModel repairsModel) throws Exception {
        repairsModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(repairsModel);
        if(num > 0){
            if(repairsModel.getResFileList()!=null && repairsModel.getResFileList().size()>0){
                resFileInter.insertBatch(repairsModel.getResFileList());
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
        int num = sqlManager.deleteById(RepairsModel.class, id);
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
    public RepairsModel selectById(String id) throws Exception {
        RepairsModel repairsModel = repairsDao.selectById(id);
        //查询资源文件
        ResFileModel params = new ResFileModel();
        params.setBussId(repairsModel.getReId());
        repairsModel.setResFileList(resFileInter.findByCondition(params));
        return repairsModel;
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<RepairsModel> findByPage(SearchParams params) throws Exception {
        PageQuery<RepairsModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("repairsModel.query",RepairsModel.class,query);
        return query;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<RepairsModel> findByCondition(RepairsModel params) throws Exception {
        return sqlManager.template(params);
    }
}
