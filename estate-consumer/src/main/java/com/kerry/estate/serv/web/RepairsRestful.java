package com.kerry.estate.serv.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kerry.core.SearchParams;
import com.kerry.estate.dto.RepairsDto;
import com.kerry.estate.serv.client.RepairsClient;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 报修服务微信接口
 * Created by wangshen on 2017/8/10.
 */
@RestController
@RequestMapping("/api/estate/open/repairs")
public class RepairsRestful {

    @Autowired
    private RepairsClient repairsClient;

    /**
     * 物业报修服务
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(@RequestBody RepairsDto repairsDto){
        return repairsClient.saveRepairs(repairsDto);
    }

    /**
     * 微信查询
     * 验证必须的参数ownId
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        if(params.getParams()==null){
            return null;
        }
        if(!params.getParams().containsKey("ownId")||params.getParams().get("ownId").equals("")){
            return null;
        }
        return repairsClient.findByPage(params, "-1");
    }

}
