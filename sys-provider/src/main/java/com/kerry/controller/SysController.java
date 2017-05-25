package com.kerry.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.ISysInter;
import com.kerry.system.model.SysModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangshen on 2017/4/7.
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ISysInter sysInter;

    @Autowired
    private DiscoveryClient client;

    /**
     * 列表查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageQuery findByPage(@RequestBody SearchParams params) throws Exception{
        ServiceInstance instance = client.getLocalServiceInstance();
        PageQuery query = sysInter.findByPage(params);
        logger.info("/sys/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param sysModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String insert(@RequestBody SysModel sysModel) throws Exception {
        return sysInter.insert(sysModel);
    }

    /**
     * 修改
     * @param sysModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody SysModel sysModel) throws Exception {
        return sysInter.update(sysModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return sysInter.delete(id);
    }
}
