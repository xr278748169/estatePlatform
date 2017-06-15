package com.kerry.controller;

import com.alibaba.fastjson.JSONObject;
import com.kerry.client.UserSysClient;
import com.kerry.system.model.UserSysModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户访问模块
 * Created by wangshen on 2017/6/12.
 */
@RestController
@RequestMapping("/api/sys/user/sys")
public class UserSysController {

    @Autowired
    private UserSysClient userSysClient;

    /**
     * 查询用户访问权限
     * @param userId
     * @return
     */
    @RequestMapping(value = "/find/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public List<UserSysModel> findByUserId(@PathVariable("userId") String userId){
        return userSysClient.findByUserId(userId);
    }

    /**
     * 用户访问权限保存
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(@RequestBody JSONObject jsonObject){
        return userSysClient.insert(jsonObject);
    }
}
