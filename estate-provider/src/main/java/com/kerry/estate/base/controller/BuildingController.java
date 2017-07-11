package com.kerry.estate.base.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.IBuildingInter;
import com.kerry.estate.base.model.BuildingModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 楼宇管理
 * Created by wangshen on 2017/7/10.
 */
@RestController
@RequestMapping("/building")
public class BuildingController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private IBuildingInter buildingInter;

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
        PageQuery query = buildingInter.findByPage(params);
        logger.info("/building/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param buildingModel
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/save", method = RequestMethod.POST)
    public String insert(@RequestBody BuildingModel buildingModel, @PathVariable("code") String code) throws Exception {
        buildingModel.setAuthCode(code);
        return buildingInter.insert(buildingModel);
    }

    /**
     * 修改
     * @param buildingModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody BuildingModel buildingModel) throws Exception {
        return buildingInter.update(buildingModel);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return buildingInter.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public BuildingModel selectById(@PathVariable("id") String id) throws Exception {
        return buildingInter.selectById(id);
    }
}
