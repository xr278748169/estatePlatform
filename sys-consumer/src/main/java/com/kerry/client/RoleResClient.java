package com.kerry.client;

import com.alibaba.fastjson.JSONObject;
import com.kerry.system.model.RoleResModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 角色资源
 * Created by wangshen on 2017/4/26.
 */
@FeignClient(name = "sys-provider")
public interface RoleResClient {

    @RequestMapping(value = "/role/res/{roleId}/list/{isRoot}", method = RequestMethod.GET)
    List<RoleResModel> findByRoleId(@PathVariable("roleId") String roleId,@PathVariable("isRoot") String isRoot);

    @RequestMapping(value = "/role/res/save", method = RequestMethod.POST)
    String save(@RequestBody JSONObject jsonObject);
}
