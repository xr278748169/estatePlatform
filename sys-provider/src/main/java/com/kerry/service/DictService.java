package com.kerry.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IDictInter;
import com.kerry.system.model.DictionaryModel;
import com.kerry.system.model.DictionaryTypeModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据字典明细
 * Created by wangshen on 2017/4/12.
 */
@Service
@Transactional
public class DictService implements IDictInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 保存
     * @param dictionaryModel
     * @return
     */
    @Override
    public String insert(DictionaryModel dictionaryModel) throws Exception {
        //查询字典类型是否重复
        DictionaryModel params = new DictionaryModel();
        params.setDictTypeCode(dictionaryModel.getDictTypeCode());
        params.setDictVaule(dictionaryModel.getDictVaule());
        List<DictionaryModel> dictList = sqlManager.template(params);
        if(dictList.size()>0){
            return ResponseEntity.createDuplicationJsonResponse(Constant.DTAT_RESULT_DUP);
        }
        int num = sqlManager.insert(dictionaryModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param dictionaryModel
     * @return
     */
    @Override
    public String update(DictionaryModel dictionaryModel) throws Exception {
        int num = sqlManager.updateTemplateById(dictionaryModel);
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
        int num = sqlManager.deleteById(DictionaryModel.class,id);
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
    public DictionaryModel selectById(String id) throws Exception {
        return sqlManager.unique(DictionaryModel.class,id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageQuery<DictionaryModel> findByPage(SearchParams params) throws Exception {
        PageQuery<DictionaryModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("dictionaryModel.query",DictionaryModel.class,query);
        return query;
    }

    /**
     * 数据字典json对象
     * @return
     */
    @Override
    public String findDictJson() throws Exception {
        JSONObject result = new JSONObject();
        JSONObject dictObj = new JSONObject();
        JSONObject dictArray = new JSONObject();
        List<DictionaryTypeModel> dictTypeList = sqlManager.all(DictionaryTypeModel.class);
        for (DictionaryTypeModel dictType : dictTypeList){
            //组织封装明细
            JSONObject dictJson = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            DictionaryModel params = new DictionaryModel();
            params.setDictTypeCode(dictType.getDictTypeCode());
            List<DictionaryModel> dictList = sqlManager.template(params);
            for (DictionaryModel dict : dictList){
                dictJson.put(dict.getDictVaule(),dict.getDictName());
                JSONObject temp = new JSONObject();
                temp.put("dictValue", dict.getDictVaule());
                temp.put("dictName", dict.getDictName());
                jsonArray.add(temp);
            }
            dictObj.put(dictType.getDictTypeCode(),dictJson);//对象
            dictArray.put(dictType.getDictTypeCode(),jsonArray);//数组对象
        }
        result.put("dictObj",dictObj);
        result.put("dictArray",dictArray);
        return ResponseEntity.createNormalJsonResponse(result);
    }
}
