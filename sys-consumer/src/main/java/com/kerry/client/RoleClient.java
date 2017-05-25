package com.kerry.client;

import com.kerry.core.SearchParams;
import com.kerry.system.model.RoleModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户角色管理
 * Created by wangshen on 2017/4/24.
 */
@FeignClient(name = "sys-provider")
public interface RoleClient {

    @RequestMapping(value = "/role/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params);

    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    String save(@RequestBody RoleModel roleModel);

    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    String update(@RequestBody RoleModel roleModel);

    @RequestMapping(value = "/role/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);
}
