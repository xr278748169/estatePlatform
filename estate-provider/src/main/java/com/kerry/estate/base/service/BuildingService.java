package com.kerry.estate.base.service;

import com.alibaba.fastjson.JSONObject;
import com.kerry.config.Constant;
import com.kerry.core.ResponseEntity;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.IBuildingInter;
import com.kerry.estate.base.inter.IBuildingRoomInter;
import com.kerry.estate.base.model.BuildingModel;
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
 * 小区楼宇管理
 * Created by wangshen on 2017/7/10.
 */
@Service
@Transactional("txManager")
public class BuildingService implements IBuildingInter {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private IBuildingRoomInter buildingRoomInter;

    /**
     * 新增
     * @param buildingModel
     * @return
     * @throws Exception
     */
    @Override
    public String insert(BuildingModel buildingModel) throws Exception {
        //判断楼号是否重复
        BuildingModel params = new BuildingModel();
        params.setBudName(buildingModel.getBudName());
        List<BuildingModel> buildingList = findByCondition(params);
        if(buildingList.size()>0){
            return ResponseEntity.createErrorJsonResponse("已存在相同楼号");
        }
        buildingModel.setCreateDate(new Date());
        int num = sqlManager.insert(buildingModel);
        if(num > 0){
            List<BuildingRoomModel> brList = new ArrayList<>();
            //按照规则生成房号具体信息 生成房号如：10101
            int cells = Integer.valueOf(buildingModel.getCells());//单元数
            int floors = Integer.valueOf(buildingModel.getFloors());//楼层数
            int floorHouseholds = Integer.valueOf(buildingModel.getFloorHouseholds());//每层户数
            for(int cell = 1; cell <= cells; cell++){//单元
                for(int floor = 1; floor <= floors; floor++){//楼层
                    String f = String.valueOf(floor);
                    if(f.length()==1){
                        f = "0"+floor;
                    }
                    String prefix = String.valueOf(cell)+String.valueOf(f);
                    for(int fhh = 1; fhh <= floorHouseholds; fhh++){
                        String room = String.valueOf(fhh);
                        if(room.length()==1){
                            room = "0"+fhh;
                        }
                        String fullRoomName = prefix+room;
                        BuildingRoomModel buildingRoom = new BuildingRoomModel();
                        buildingRoom.setBudId(buildingModel.getBudId());
                        buildingRoom.setCellName(String.valueOf(cell));
                        buildingRoom.setFloor(String.valueOf(floor));
                        buildingRoom.setRoomName(fullRoomName);
                        buildingRoom.setAuthCode(buildingModel.getAuthCode());
                        buildingRoom.setCreateDate(new Date());
                        buildingRoom.setCreateUser(buildingModel.getCreateUser());
                        brList.add(buildingRoom);
                    }
                }
            }
            buildingRoomInter.insertBatch(brList);
            return ResponseEntity.createNormalJsonResponse(Constant.DATA_RESULT_SUCCESS);
        }
        return ResponseEntity.createErrorJsonResponse(Constant.DATA_RESULT_ERROR);
    }

    /**
     * 修改
     * @param buildingModel
     * @return
     * @throws Exception
     */
    @Override
    public String update(BuildingModel buildingModel) throws Exception {
        buildingModel.setUpdateDate(new Date());
        int num = sqlManager.updateTemplateById(buildingModel);
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
        int num = sqlManager.deleteById(BuildingModel.class, id);
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
    public BuildingModel selectById(String id) throws Exception {
        return sqlManager.unique(BuildingModel.class, id);
    }

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public PageQuery<BuildingModel> findByPage(SearchParams params) throws Exception {
        PageQuery<BuildingModel> query = new PageQuery<>();
        query.setPageNumber(params.getPage());
        query.setPageSize(params.getPageSize());
        query.setParas(params.getParams());
        sqlManager.pageQuery("buildingModel.query",BuildingModel.class,query);
        return query;
    }

    /**
     * 条件查询
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<BuildingModel> findByCondition(BuildingModel params) throws Exception {
        return sqlManager.template(params);
    }

    /**
     * 查询全部转为json
     * @return
     * @throws Exception
     */
    @Override
    public List<JSONObject> findAllToJson(String comId) throws Exception {
        List<JSONObject> result = new ArrayList<>();
        BuildingModel params = new BuildingModel();
        params.setComId(comId);
        List<BuildingModel> budList = this.findByCondition(params);
        Collections.sort(budList,(o1, o2) -> (o1.getBudName().compareTo(o2.getBudName())));
        for (BuildingModel bud : budList) {
            JSONObject budJson = new JSONObject();
            budJson.put("value", bud.getBudId());
            budJson.put("label", bud.getBudName()+"号楼");
            budJson.put("cells", bud.getCells());
            budJson.put("floors", bud.getFloors());
            result.add(budJson);
        }
        return result;
    }
}
