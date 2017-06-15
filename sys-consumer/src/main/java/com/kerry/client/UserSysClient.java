package com.kerry.client;

import com.alibaba.fastjson.JSONObject;
import com.kerry.system.model.UserSysModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 用户访问权限
 * Created by wangshen on 2017/6/12.
 */
@FeignClient(name = "sys-provider")
public interface UserSysClient {

    @RequestMapping(value = "/user/sys/find/{userId}", method = RequestMethod.GET)
    List<UserSysModel> findByUserId(@PathVariable("userId") String userId);

    @RequestMapping(value = "/user/sys/save", method = RequestMethod.POST)
    String insert(@RequestBody JSONObject jsonObject);
}
