package com.kerry.controller;

import com.kerry.client.ResClient;
import com.kerry.core.SearchParams;
import com.kerry.system.model.ResModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统资源管理
 * Created by wangshen on 2017/4/24.
 */
@RestController
@RequestMapping("/api/sys/res")
public class ResController {

    @Autowired
    private ResClient resClient;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return resClient.findByPage(params);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody ResModel resModel){
        return resClient.save(resModel);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody ResModel resModel){
        return resClient.update(resModel);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return resClient.delete(id);
    }

    @RequestMapping(value = "/list/sub/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public List<ResModel> findByParentId(@PathVariable("parentId") String parentId){
        return resClient.findByParentId(parentId);
    }
}
