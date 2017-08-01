package com.kerry.estate.base.service;

import com.alibaba.fastjson.JSONObject;
import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.IBuildingRoomInter;
import com.kerry.estate.base.model.BuildingRoomModel;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 楼宇房间管理
 * Created by wangshen on 2017/7/10.
 */
@Service
@Transactional("txManager")
public class BuildingRoomService implements IBuildingRoomInter {

    @Autowired
    private SQLManager sqlManager;

    /**
     * 新增
     * @param buildingRoomModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(BuildingRoomModel buildingRoomModel) throws Exception {
        //查询是否信息重复
        BuildingRoomModel params = new BuildingRoomModel();
        params.setCellName(buildingRoomModel.getCellName());
        params.setFloor(buildingRoomModel.getFloor());
        params.setRoomName(buildingRoomModel.getRoomName());
        params.setBudId(buildingRoomModel.getBudId());
        List<BuildingRoomModel> brList = findByCondition(params);
        if(brList.size()>0){
            return ResponseEntity.createErrorJsonResponse("当前房号已经存在");
        }
        buildingRoomModel.setCreateDate(new Date());
        int num = sqlManager.insert(buildingRoomModel);
        if(num > 0){
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param buildingRoomModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(BuildingRoomModel buildingRoomModel) throws Exception {
        buildingRoomModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(buildingRoomModel);
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
        int num = sqlManager.deleteById(BuildingRoomModel.class, id);
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
    public BuildingRoomModel selectById(String id) throws Exception {
        return sqlManager.unique(BuildingRoomModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<BuildingRoomModel> findByPage(SearchParams params) throws Exception {
        PageQuery<BuildingRoomModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("buildingRoomModel.query",BuildingRoomModel.class,query);
        return query;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<BuildingRoomModel> findByCondition(BuildingRoomModel params) throws Exception {
        return sqlManager.template(params);
    }

    /**
     * 批量写入
     * @param brList
     * @throws Exception
     */
    @Override
    public void insertBatch(List<BuildingRoomModel> brList) throws Exception {
        sqlManager.insertBatch(BuildingRoomModel.class, brList);
    }

    /**
     * 根据条件查询并且转为json
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<JSONObject> findByConditionToJson(BuildingRoomModel params) throws Exception {
        List<JSONObject> result = new ArrayList<>();
        List<BuildingRoomModel> brList = this.findByCondition(params);
        Collections.sort(brList,(o1 , o2) -> (o1.getCellName().compareTo(o2.getCellName())));
        for(BuildingRoomModel br : brList) {
            JSONObject brJson = new JSONObject();
            brJson.put("value", br.getBurId());
            brJson.put("label", br.getRoomName()+"号");
            brJson.put("cell", br.getCellName());
            brJson.put("floor", br.getFloor());
            result.add(brJson);
        }
        return result;
    }
}
