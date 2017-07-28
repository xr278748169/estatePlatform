package com.kerry.estate.base.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.dao.ResFileDao;
import com.kerry.estate.base.inter.IResFileInter;
import com.kerry.estate.base.model.ResFileModel;
import com.kerry.estate.base.model.base.ResFile;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 资源文件管理
 * Created by wangshen on 2017/7/27.
 */
@Service
@Transactional("txManager")
public class ResFileService implements IResFileInter {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private ResFileDao resFileDao;

    /**
     * 保存
     * @param resFileModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(ResFileModel resFileModel) throws Exception {
        resFileModel.setCreateDate(new Date());
        int num = sqlManager.insert(resFileModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param resFileModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(ResFileModel resFileModel) throws Exception {
        resFileModel.setUpdateDate(new Date());
        int num = sqlManager.insert(resFileModel);
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
        int num = sqlManager.deleteById(ResFileModel.class, id);
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
    public ResFileModel selectById(String id) throws Exception {
        return sqlManager.unique(ResFileModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<ResFileModel> findByPage(SearchParams params) throws Exception {
        return null;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<ResFileModel> findByCondition(ResFileModel params) throws Exception {
        return sqlManager.template(params);
    }

    /**
     * 资源文件批量保存
     * @param resFileList
     */
    @Override
    public void insertBatch(List<ResFileModel> resFileList) throws Exception {
        //先删除原有的
        String bussId = resFileList.get(0).getBussId();
        int num = resFileDao.deleteByBussId(bussId);
        if(num > 0){
            sqlManager.insertBatch(ResFileModel.class, resFileList);
        }
    }

    /**
     * 批量删除资源
     * @param bussId
     * @return
     * @throws Exception
     */
    @Override
    public int deleteByBussId(String bussId) throws Exception {
        return resFileDao.deleteByBussId(bussId);
    }
}
