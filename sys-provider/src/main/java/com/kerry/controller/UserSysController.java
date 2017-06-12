package com.kerry.controller;

import com.alibaba.fastjson.JSONObject;
import com.kerry.system.inter.IUserSysInter;
import com.kerry.system.model.UserSysModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户访问模块
 * Created by wangshen on 2017/6/12.
 */
@RestController
@RequestMapping("/user/sys")
public class UserSysController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private IUserSysInter userSysInter;

    /**
     * 查询用户访问权限
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/find/{userId}", method = RequestMethod.GET)
    public List<UserSysModel> findByUserId(@PathVariable("userId") String userId) throws Exception {
        return userSysInter.findByUserId(userId);
    }

    /**
     * 用户访问权限保存
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String insert(@RequestBody JSONObject jsonObject) throws Exception {
        return userSysInter.insertBatch(jsonObject);
    }
}
