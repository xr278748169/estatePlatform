package com.kerry.estate.base.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.IPropertyCoInter;
import com.kerry.estate.base.model.PropertyCoModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 物业公司管理
 * Created by wangshen on 2017/7/3.
 */
@RestController
@RequestMapping("/property-co")
public class PropertyCoController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IPropertyCoInter propertyCoInter;

    @Autowired
    private DiscoveryClient client;

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/list", method = RequestMethod.POST)
    public PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code) throws Exception{
        ServiceInstance instance = client.getLocalServiceInstance();
        if(params.getParams()==null){
            params.setParams(new HashMap());
        }
        params.getParams().put("authCode", code);
        PageQuery query = propertyCoInter.findByPage(params);
        logger.info("/property-co/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param propertyCoModel
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/save", method = RequestMethod.POST)
    public String insert(@RequestBody PropertyCoModel propertyCoModel, @PathVariable("code") String code) throws Exception {
        propertyCoModel.setAuthCode(code);
        return propertyCoInter.insert(propertyCoModel);
    }

    /**
     * 修改
     * @param propertyCoModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody PropertyCoModel propertyCoModel) throws Exception {
        return propertyCoInter.update(propertyCoModel);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return propertyCoInter.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public PropertyCoModel selectById(@PathVariable("id") String id) throws Exception {
        return propertyCoInter.selectById(id);
    }

    /**
     * 查询全部
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<PropertyCoModel> findAll() throws Exception {
        return propertyCoInter.findAll();
    }
}
