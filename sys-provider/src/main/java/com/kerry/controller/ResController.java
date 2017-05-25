package com.kerry.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IResInter;
import com.kerry.system.model.ResModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源管理
 * Created by wangshen on 2017/4/24.
 */
@RestController
@RequestMapping("/res")
public class ResController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IResInter resInter;

    @Autowired
    private DiscoveryClient client;

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageQuery findByPage(@RequestBody SearchParams params) throws Exception {
        ServiceInstance instance = client.getLocalServiceInstance();
        PageQuery query = resInter.findByPage(params);
        logger.info("/sys/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param resModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String insert(@RequestBody ResModel resModel) throws Exception {
        return resInter.insert(resModel);
    }

    /**
     * 修改
     * @param resModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody ResModel resModel) throws Exception {
        return resInter.update(resModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return resInter.delete(id);
    }

    /**
     * 按照级别查询
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/list/sub/{parentId}", method = RequestMethod.GET)
    public List<ResModel> findByParentId(@PathVariable("parentId") String parentId) throws Exception {
        return resInter.findByParentId(parentId);
    }
}
