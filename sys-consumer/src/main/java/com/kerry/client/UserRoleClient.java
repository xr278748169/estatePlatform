package com.kerry.client;

import com.kerry.system.model.UserRoleModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户角色服务调用
 * Created by wangshen on 2017/5/8.
 */
@FeignClient(name = "sys-provider")
public interface UserRoleClient {

    @RequestMapping(value = "/user/role/select", method = RequestMethod.POST)
    List<UserRoleModel> select(@RequestBody UserRoleModel params);

    @RequestMapping(value = "/user/role/select/{id}", method = RequestMethod.GET)
    UserRoleModel selectById(String id);
}
