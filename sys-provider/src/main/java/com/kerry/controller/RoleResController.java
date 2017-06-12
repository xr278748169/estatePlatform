package com.kerry.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.service.RoleResService;
import com.kerry.system.model.RoleResModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangshen on 2017/4/26.
 */
@RestController
@RequestMapping("/role/res")
public class RoleResController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private RoleResService roleResService;

    @Autowired
    private DiscoveryClient client;

    /**
     * 角色资源列表
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}/list/{isRoot}", method = RequestMethod.GET)
    public List<RoleResModel> findByRoleId(@PathVariable("roleId") String roleId,@PathVariable("isRoot") String isRoot){
        ServiceInstance instance = client.getLocalServiceInstance();
        List<RoleResModel> roleResList = roleResService.findByRoleId(roleId,isRoot);
        logger.info("/sys/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(roleResList));
        return roleResList;
    }

    /**
     * 角色资源保存
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody JSONObject jsonObject){
        return roleResService.insertBatch(jsonObject);
    }
}
