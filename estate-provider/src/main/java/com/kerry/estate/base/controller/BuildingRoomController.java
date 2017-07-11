package com.kerry.estate.base.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.IBuildingRoomInter;
import com.kerry.estate.base.model.BuildingRoomModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 楼宇房间管理
 * Created by wangshen on 2017/7/10.
 */
@RestController
@RequestMapping("/building/room")
public class BuildingRoomController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private IBuildingRoomInter buildingRoomInter;

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/list", method = RequestMethod.POST)
    public PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code) throws Exception{
        ServiceInstance instance = client.getLocalServiceInstance();
        if(params.getParams()==null){
            params.setParams(new HashMap());
        }
        params.getParams().put("authCode", code);
        PageQuery query = buildingRoomInter.findByPage(params);
        logger.info("/building/room/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param buildingRoomModel
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/save", method = RequestMethod.POST)
    public String insert(@RequestBody BuildingRoomModel buildingRoomModel, @PathVariable("code") String code) throws Exception {
        buildingRoomModel.setAuthCode(code);
        return buildingRoomInter.insert(buildingRoomModel);
    }

    /**
     * 修改
     * @param buildingRoomModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody BuildingRoomModel buildingRoomModel) throws Exception {
        return buildingRoomInter.update
                (buildingRoomModel);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return buildingRoomInter.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public BuildingRoomModel selectById(@PathVariable("id") String id) throws Exception {
        return buildingRoomInter.selectById(id);
    }
}
