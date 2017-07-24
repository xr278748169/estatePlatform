package com.kerry.estate.msg.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.estate.msg.inter.IEssayInter;
import com.kerry.estate.msg.model.EssayModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 物业新闻消息
 * Created by wangshen on 2017/7/24.
 */
@RestController
@RequestMapping("/essay")
public class EssayController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private IEssayInter essayInter;

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
        PageQuery query = essayInter.findByPage(params);
        logger.info("/essay/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 保存
     * @param essayModel
     * @param code
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/save", method = RequestMethod.POST)
    public String insert(@RequestBody EssayModel essayModel, @PathVariable("code") String code) throws Exception {
        essayModel.setAuthCode(code);
        return essayInter.insert(essayModel);
    }

    /**
     * 修改
     * @param essayModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody EssayModel essayModel) throws Exception {
        return essayInter.update(essayModel);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return essayInter.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public EssayModel selectById(@PathVariable("id") String id) throws Exception {
        return essayInter.selectById(id);
    }
}
