package com.kerry.estate.serv.controller;

import com.kerry.core.SearchParams;
import com.kerry.estate.serv.client.SuitClient;
import com.kerry.estate.serv.model.SuitModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 投诉建议
 * Created by wangshen on 2017/7/27.
 */
@RestController
@RequestMapping("/api/estate/suit")
public class SuitController {

    @Autowired
    private SuitClient suitClient;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(HttpServletRequest request, SearchParams params){
        String code = request.getAttribute("code").toString();
        return suitClient.findByPage(params, code);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SuitModel selectById(@PathVariable("id") String id){
        return suitClient.selectById(id);
    }

    /**
     * 更新
     * @param suitModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody SuitModel suitModel){
        return suitClient.update(suitModel);
    }
}
