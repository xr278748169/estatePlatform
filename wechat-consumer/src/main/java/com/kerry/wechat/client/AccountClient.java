package com.kerry.wechat.client;

import com.kerry.core.SearchParams;
import com.kerry.wechat.model.AccountModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信公众号管理
 * Created by wangshen on 2017/6/20.
 */
@FeignClient(name = "wechat-provider")
public interface AccountClient {

    @RequestMapping(value = "/account/{code}/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code);

    @RequestMapping(value = "/account/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody AccountModel accountModel, @PathVariable("code") String code);

    @RequestMapping(value = "/account/update", method = RequestMethod.POST)
    String update(@RequestBody AccountModel accountModel);

    @RequestMapping(value = "/account/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/account/select/{id}", method = RequestMethod.GET)
    AccountModel selectById(@PathVariable("id") String id);

}
