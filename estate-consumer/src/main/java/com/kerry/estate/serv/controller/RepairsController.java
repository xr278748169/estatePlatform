package com.kerry.estate.serv.controller;

import com.kerry.core.SearchParams;
import com.kerry.estate.serv.client.RepairsClient;
import com.kerry.estate.serv.model.RepairsModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 报修管理
 * Created by wangshen on 2017/7/27.
 */
@RestController
@RequestMapping("/api/estate/repairs")
public class RepairsController {

    @Autowired
    private RepairsClient repairsClient;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(HttpServletRequest request, SearchParams params){
        String code = request.getAttribute("code").toString();
        return repairsClient.findByPage(params, code);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RepairsModel selectById(@PathVariable("id") String id){
        return repairsClient.selectById(id);
    }

    /**
     * 更新
     * @param repairsModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody RepairsModel repairsModel){
        return repairsClient.update(repairsModel);
    }
}
