package com.kerry.wechat.client;

import com.kerry.core.SearchParams;
import com.kerry.wechat.model.ConstantModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 微信配置信息
 * Created by wangshen on 2017/6/21.
 */
@FeignClient(name = "wechat-provider")
public interface ConstantClient {

    @RequestMapping(value = "/constant/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params);

    @RequestMapping(value = "/constant/save", method = RequestMethod.POST)
    String insert(@RequestBody ConstantModel constantModel);

    @RequestMapping(value = "/constant/update", method = RequestMethod.POST)
    String update(@RequestBody ConstantModel constantModel);

    @RequestMapping(value = "/constant/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/constant/select/{id}", method = RequestMethod.GET)
    ConstantModel selectById(@PathVariable("id") String id);

}
