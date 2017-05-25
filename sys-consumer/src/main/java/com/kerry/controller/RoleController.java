package com.kerry.controller;

import com.kerry.client.RoleClient;
import com.kerry.core.SearchParams;
import com.kerry.system.model.RoleModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 * Created by wangshen on 2017/4/24.
 */
@RestController
@RequestMapping("/api/sys/role")
public class RoleController {

    @Autowired
    private RoleClient roleClient;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return roleClient.findByPage(params);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody RoleModel roleModel){
        return roleClient.save(roleModel);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody RoleModel roleModel){
        return roleClient.update(roleModel);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return roleClient.delete(id);
    }
}
