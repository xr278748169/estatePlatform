package com.kerry.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.wechat.inter.IConstantInter;
import com.kerry.wechat.model.ConstantModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by wangshen on 2017/6/21.
 */
@RestController
@RequestMapping("/constant")
public class ConstantController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IConstantInter constantInter;

    @Autowired
    private DiscoveryClient client;

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageQuery findByPage(@RequestBody SearchParams params) throws Exception{
        ServiceInstance instance = client.getLocalServiceInstance();
        PageQuery query = constantInter.findByPage(params);
        logger.info("/account/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 公众号信息保存
     * @param constantModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String insert(@RequestBody ConstantModel constantModel) throws Exception {
        return constantInter.insert(constantModel);
    }

    /**
     * 公众号信息修改
     * @param constantModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody ConstantModel constantModel) throws Exception {
        return constantInter.update(constantModel);
    }

    /**
     * 公众号信息删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return constantInter.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public ConstantModel selectById(@PathVariable("id") String id) throws Exception {
        return constantInter.selectById(id);
    }
}
