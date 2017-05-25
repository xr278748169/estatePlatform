package com.kerry.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IRoleInter;
import com.kerry.system.model.RoleModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 * Created by wangshen on 2017/4/24.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IRoleInter roleInter;

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
        PageQuery query = roleInter.findByPage(params);
        logger.info("/sys/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param roleModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String insert(@RequestBody RoleModel roleModel) throws Exception {
        return roleInter.insert(roleModel);
    }

    /**
     * 修改
     * @param roleModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody RoleModel roleModel) throws Exception {
        return roleInter.update(roleModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return roleInter.delete(id);
    }

}
