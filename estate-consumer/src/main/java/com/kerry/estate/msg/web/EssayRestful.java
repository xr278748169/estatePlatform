package com.kerry.estate.msg.web;

import com.kerry.core.SearchParams;
import com.kerry.estate.msg.client.EssayClient;
import com.kerry.estate.msg.model.EssayModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 物业微信前端接口
 * Created by wangshen on 2017/8/11.
 */
@RestController
@RequestMapping("/api/estate/open/essay")
public class EssayRestful {

    @Autowired
    private EssayClient ownerClient;

    /**
     * 微信端分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return ownerClient.findByPage(params, "-1");
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public EssayModel selectById(@PathVariable("id") String id){
        return ownerClient.selectById(id);
    }
}
