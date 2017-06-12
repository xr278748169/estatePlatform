package com.kerry.controller;

import com.alibaba.fastjson.JSONObject;
import com.kerry.client.RoleResClient;
import com.kerry.system.model.RoleResModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色资源
 * Created by wangshen on 2017/4/26.
 */
@RestController
@RequestMapping("/api/sys/role/res")
public class RoleResController {

    @Autowired
    private RoleResClient roleResClient;

    @RequestMapping(value = "/list/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public List<String> findByRoleId(@PathVariable("roleId") String roleId){
        List<RoleResModel> roleResList = roleResClient.findByRoleId(roleId,"no");
        List<String> resIdList = new ArrayList<>();
        for (RoleResModel roleRes : roleResList){
            resIdList.add(roleRes.getResId());
        }
        return resIdList;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody JSONObject jsonObject){
        return roleResClient.save(jsonObject);
    }

}
