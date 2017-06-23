package com.kerry.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.kerry.core.SearchParams;
import com.kerry.wechat.inter.IAccountInter;
import com.kerry.wechat.model.AccountModel;
import org.apache.log4j.Logger;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Created by wangshen on 2017/6/19.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private IAccountInter accountInter;

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
        PageQuery query = accountInter.findByPage(params);
        logger.info("/account/list, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + JSON.toJSONString(query));
        return query;
    }

    /**
     * 公众号信息保存
     * @param accountModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{code}/save", method = RequestMethod.POST)
    public String insert(@RequestBody AccountModel accountModel, @PathVariable("code") String code) throws Exception {
        accountModel.setAuthCode(code);
        return accountInter.insert(accountModel);
    }

    /**
     * 公众号信息修改
     * @param accountModel
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody AccountModel accountModel) throws Exception {
        return accountInter.update(accountModel);
    }

    /**
     * 公众号信息删除
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id) throws Exception{
        return accountInter.delete(id);
    }

    /**
     * 主键查询
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/select/{id}", method = RequestMethod.GET)
    public AccountModel selectById(@PathVariable("id") String id) throws Exception {
        return accountInter.selectById(id);
    }
}
