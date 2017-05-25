package com.kerry.client;

import com.kerry.core.SearchParams;
import com.kerry.system.model.SysModel;
import com.kerry.system.model.SysModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 调用应用管理服务
 * Created by wangshen on 2017/4/20.
 */
@FeignClient(name = "sys-provider")
public interface SysClient {

    @RequestMapping(value = "/sys/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params);

    @RequestMapping(value = "/sys/save", method = RequestMethod.POST)
    String save(@RequestBody SysModel sysModel);

    @RequestMapping(value = "/sys/update", method = RequestMethod.POST)
    String update(@RequestBody SysModel sysModel);

    @RequestMapping(value = "/sys/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);
}
