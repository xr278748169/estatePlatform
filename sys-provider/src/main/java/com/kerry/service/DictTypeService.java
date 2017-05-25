package com.kerry.service;

import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IDictTypeInter;
import com.kerry.system.model.DictionaryModel;
import com.kerry.system.model.DictionaryTypeModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典类型
 * Created by wangshen on 2017/4/12.
 */
@Service
@Transactional
public class DictTypeService implements IDictTypeInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 保存
     * @param dictionaryTypeModel
     * @return
     */
    @Override
    public String insert(DictionaryTypeModel dictionaryTypeModel) throws Exception {
        int num = sqlManager.insert(dictionaryTypeModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param dictionaryTypeModel
     * @return
     */
    @Override
    public String update(DictionaryTypeModel dictionaryTypeModel) throws Exception {
        int num = sqlManager.updateTemplateById(dictionaryTypeModel);
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
        DictionaryTypeModel dictType = selectById(id);
        DictionaryModel params = new DictionaryModel();
        params.setDictTypeCode(dictType.getDictTypeCode());
        List<DictionaryModel> dictList = sqlManager.template(params);
        if(dictList.size()>0){
            return ResponseEntity.createErrorJsonResponse(Constant.DTAT_RESULT_SUB);
        }
        int num = sqlManager.deleteById(DictionaryTypeModel.class,id);
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
    public DictionaryTypeModel selectById(String id) throws Exception {
        return sqlManager.unique(DictionaryTypeModel.class,id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageQuery<DictionaryTypeModel> findByPage(SearchParams params) throws Exception {
        PageQuery<DictionaryTypeModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("dictionaryTypeModel.query",DictionaryTypeModel.class,query);
        return query;
    }
}
