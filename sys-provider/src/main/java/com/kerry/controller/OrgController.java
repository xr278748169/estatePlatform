package com.kerry.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IOrgInter;
import com.kerry.system.model.OrgModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织机构
 * Created by wangshen on 2017/6/5.
 */
@RestController
@RequestMapping("/org")
public class OrgController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private IOrgInter orgInter;

    /**
     * 分页查询
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageQuery findByPage(@RequestBody SearchParams params) throws Exception {
        ServiceInstance instance = client.getLocalServiceInstance();
        PageQuery query = orgInter.findByPage(params);
        logger.info("/org/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param orgModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String insert(@RequestBody OrgModel orgModel) throws Exception {
        return orgInter.insert(orgModel);
    }

    /**
     * 修改
     * @param orgModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody OrgModel orgModel) throws Exception {
        return orgInter.update(orgModel);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception {
        return orgInter.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public OrgModel selectById(@PathVariable("id") String id) throws Exception {
        return orgInter.selectById(id);
    }

    /**
     * 组织机构树查询
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list/tree", method = RequestMethod.GET)
    public List<OrgModel> findTreeList() throws Exception {
        return orgInter.findTreeList();
    }
}
