package com.kerry.controller;

import com.kerry.client.OrgClient;
import com.kerry.core.SearchParams;
import com.kerry.system.model.OrgModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织机构
 * Created by wangshen on 2017/6/5.
 */
@RestController
@RequestMapping("/api/sys/org")
public class OrgController {

    @Autowired
    private OrgClient orgClient;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return orgClient.findByPage(params);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody OrgModel orgModel){
        return orgClient.insert(orgModel);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody OrgModel orgModel){
        return orgClient.update(orgModel);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return orgClient.delete(id);
    }

    @RequestMapping(value = "/list/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<OrgModel> findTreeList(){
        return orgClient.findTreeList();
    }

}
