package com.kerry.estate.base.web;

import com.alibaba.fastjson.JSONObject;
import com.kerry.estate.base.client.BuildingClient;
import com.kerry.estate.base.client.BuildingRoomClient;
import com.kerry.estate.base.client.CommunityClient;
import com.kerry.estate.base.model.BuildingModel;
import com.kerry.estate.base.model.BuildingRoomModel;
import com.kerry.estate.base.model.CommunityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * 小区微信接口
 * Created by wangshen on 2017/7/31.
 */
@RestController
@RequestMapping("/api/estate/open/community")
public class CommunityRestful {

    @Autowired
    private CommunityClient communityClient;

    @Autowired
    private BuildingClient buildingClient;

    @Autowired
    private BuildingRoomClient buildingRoomClient;

    /**
     * 获取全部小区
     * @return
     */
    @RequestMapping(value = "/all/json", method = RequestMethod.GET)
    @ResponseBody
    public List<JSONObject> findAllToJson(){
        return communityClient.findAllToJson();
    }

    /**
     * 获取小区的楼栋信息
     * @param comId
     * @return
     */
    @RequestMapping(value = "/building/{comId}", method = RequestMethod.GET)
    @ResponseBody
    public List<JSONObject> findByComId(@PathVariable("comId") String comId){
        return buildingClient.findAllToJson(comId);
    }

    /**
     * 获取楼宇详细信息
     * @param budId
     * @return
     */
    @RequestMapping(value = "/building/room/{budId}/{cell}/{floor}", method = RequestMethod.GET)
    @ResponseBody
    public List<JSONObject> findBudRoom(@PathVariable("budId") String budId,
                                        @PathVariable("cell") String cell,
                                        @PathVariable("floor") String floor){
        BuildingRoomModel params = new BuildingRoomModel();
        params.setBudId(budId);
        params.setCellName(cell);
        params.setFloor(floor);
        return  buildingRoomClient.findByConditionToJson(params);
    }
}
