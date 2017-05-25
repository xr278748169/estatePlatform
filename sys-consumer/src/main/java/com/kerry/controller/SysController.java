package com.kerry.controller;

import com.kerry.client.SysClient;
import com.kerry.core.SearchParams;
import com.kerry.system.model.SysModel;
import com.kerry.system.model.SysModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 应用管理
 * Created by wangshen on 2017/4/7.
 */
@RestController
@RequestMapping("/api/sys/sys")
public class SysController {

    @Autowired
    private SysClient sysClient;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return sysClient.findByPage(params);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody SysModel sysModel){
        return sysClient.save(sysModel);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody SysModel sysModel){
        return sysClient.update(sysModel);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return sysClient.delete(id);
    }

}
