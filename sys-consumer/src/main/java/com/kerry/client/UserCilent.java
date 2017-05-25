package com.kerry.client;

import com.kerry.core.SearchParams;
import com.kerry.system.model.UserModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户信息服务调用
 * Created by wangshen on 2017/5/8.
 */
@FeignClient(name = "sys-provider")
public interface UserCilent {

    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params);

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    String insert(@RequestBody UserModel userModel);

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    String update(@RequestBody UserModel userModel);

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/user/select", method = RequestMethod.POST)
    List<UserModel> select(@RequestBody UserModel params);

    @RequestMapping(value = "/user/select/{id}", method = RequestMethod.GET)
    UserModel selectById(String id);
}
