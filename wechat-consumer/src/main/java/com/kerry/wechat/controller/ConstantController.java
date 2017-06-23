package com.kerry.wechat.controller;

import com.kerry.core.SearchParams;
import com.kerry.wechat.client.ConstantClient;
import com.kerry.wechat.model.ConstantModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信配置管理
 * Created by wangshen on 2017/6/21.
 */
@RestController
@RequestMapping("/api/wechat/constant")
public class ConstantController {

    @Autowired
    private ConstantClient constantClient;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageQuery findByPage(SearchParams params){
        return constantClient.findByPage(params);
    }

    /**
     * 保存
     * @param constantModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String insert(@RequestBody ConstantModel constantModel){
        return constantClient.insert(constantModel);
    }

    /**
     * 更新
     * @param constantModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public String update(@RequestBody ConstantModel constantModel){
        return constantClient.update(constantModel);
    }

    /**
     * 主键删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id){
        return constantClient.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ConstantModel selectById(@PathVariable("id") String id){
        return constantClient.selectById(id);
    }
}
