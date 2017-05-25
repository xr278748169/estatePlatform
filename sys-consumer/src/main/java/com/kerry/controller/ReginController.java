package com.kerry.controller;

import com.kerry.client.ReginClient;
import com.kerry.core.SearchParams;
import com.kerry.system.model.RegionModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangshen on 2017/4/21.
 */
@RestController
@RequestMapping("/api/sys/regin")
public class ReginController {

    @Autowired
    private ReginClient reginClient;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return reginClient.findByPage(params);
    }

    @RequestMapping(value = "/find/{level}/level", method = RequestMethod.GET)
    @ResponseBody
    public List<RegionModel> findByLevel(@PathVariable("level") String level) {
        return reginClient.findByLevel(level);
    }
}
