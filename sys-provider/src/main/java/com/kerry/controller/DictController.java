package com.kerry.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.system.inter.IDictInter;
import com.kerry.system.model.DictionaryModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangshen on 2017/4/21.
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IDictInter dictInter;

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
        PageQuery query = dictInter.findByPage(params);
        logger.info("/sys/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param dictionaryModel
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String insert(@RequestBody DictionaryModel dictionaryModel) throws Exception {
        return dictInter.insert(dictionaryModel);
    }

    /**
     * 修改
     * @param dictionaryModel
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody DictionaryModel dictionaryModel) throws Exception {
        return dictInter.update(dictionaryModel);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return dictInter.delete(id);
    }

    /**
     * 获取字典json对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public String findDictJson() throws Exception {
        return dictInter.findDictJson();
    }

}
