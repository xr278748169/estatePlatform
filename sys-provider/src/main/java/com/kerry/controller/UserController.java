package com.kerry.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IUserInter;
import com.kerry.system.model.UserModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 * Created by wangshen on 2017/5/8.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private IUserInter userInter;

    /**
     * 用户信息分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageQuery findByPage(@RequestBody SearchParams params) throws Exception {
        ServiceInstance instance = client.getLocalServiceInstance();
        PageQuery query = userInter.findByPage(params);
        logger.info("/sys/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 用户信息保存
     * @param userModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String insert(@RequestBody UserModel userModel) throws Exception {
        return userInter.insert(userModel);
    }

    /**
     * 用户信息修改
     * @param userModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody UserModel userModel) throws Exception {
        return userInter.update(userModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return userInter.delete(id);
    }

    /**
     * 用户查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select", method = RequestMethod.POST)
    public List<UserModel> select(@RequestBody UserModel params) throws Exception {
        return userInter.select(params);
    }

    /**
     * 根据userId查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public UserModel selectById(String id) throws Exception {
        return userInter.selectById(id);
    }
}
