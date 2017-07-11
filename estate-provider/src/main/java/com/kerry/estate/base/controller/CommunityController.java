package com.kerry.estate.base.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.inter.ICommunityInter;
import com.kerry.estate.base.model.CommunityModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 小区管理
 * Created by wangshen on 2017/7/4.
 */
@RestController
@RequestMapping("/community")
public class CommunityController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ICommunityInter communityInter;

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
        PageQuery query = communityInter.findByPage(params);
        logger.info("/community/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param communityModel
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/save", method = RequestMethod.POST)
    public String insert(@RequestBody CommunityModel communityModel, @PathVariable("code") String code) throws Exception {
        communityModel.setAuthCode(code);
        return communityInter.insert(communityModel);
    }

    /**
     * 修改
     * @param communityModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody CommunityModel communityModel) throws Exception {
        return communityInter.update(communityModel);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return communityInter.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public CommunityModel selectById(@PathVariable("id") String id) throws Exception {
        return communityInter.selectById(id);
    }
}
