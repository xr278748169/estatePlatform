package com.kerry.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IDictTypeInter;
import com.kerry.system.inter.IReginInter;
import com.kerry.system.model.RegionModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangshen on 2017/4/21.
 */
@RestController
@RequestMapping("/regin")
public class ReginController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IReginInter reginInter;

    @Autowired
    private DiscoveryClient client;

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageQuery findByPage(@RequestBody SearchParams params) throws Exception{
        ServiceInstance instance = client.getLocalServiceInstance();
        PageQuery query = reginInter.findByPage(params);
        logger.info("/sys/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 分级别查询
     * @param level
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/find/{level}/level", method = RequestMethod.GET)
    public List<RegionModel> findByLevel(@PathVariable("level") String level) throws Exception {
        ServiceInstance instance = client.getLocalServiceInstance();
        List<RegionModel> regionList = reginInter.findByLevel(level);
        logger.info("/sys/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(regionList));
        return regionList;
    }

}
