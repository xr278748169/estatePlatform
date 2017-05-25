package com.kerry.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IUserRoleInter;
import com.kerry.system.model.UserRoleModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户角色信息
 * Created by wangshen on 2017/5/8.
 */
@RestController
@RequestMapping("/user/role")
public class UserRoleController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private IUserRoleInter userRoleInter;


    /**
     * 用户查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public List<UserRoleModel> select(@RequestBody UserRoleModel params) throws Exception {
        return userRoleInter.select(params);
    }

    /**
     * 根据userId查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public UserRoleModel selectById(String id) throws Exception {
        return userRoleInter.selectById(id);
    }
}
