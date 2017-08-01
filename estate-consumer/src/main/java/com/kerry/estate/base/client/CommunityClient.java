package com.kerry.estate.base.client;

import com.alibaba.fastjson.JSONObject;
import com.kerry.core.SearchParams;
import com.kerry.estate.base.model.CommunityModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 小区管理
 * Created by wangshen on 2017/7/4.
 */
@FeignClient(name = "estate-provider")
public interface CommunityClient {

    @RequestMapping(value = "/community/{code}/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code);

    @RequestMapping(value = "/community/all", method = RequestMethod.POST)
    List<CommunityModel> findAll();

    @RequestMapping(value = "/community/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody CommunityModel communityModel, @PathVariable("code") String code);

    @RequestMapping(value = "/community/update", method = RequestMethod.POST)
    String update(@RequestBody CommunityModel communityModel);

    @RequestMapping(value = "/community/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/community/select/{id}", method = RequestMethod.GET)
    CommunityModel selectById(@PathVariable("id") String id);

    @RequestMapping(value = "/community/find", method = RequestMethod.POST)
    List<CommunityModel> findByCondition(@RequestBody CommunityModel params);

    @RequestMapping(value = "/community/all/json", method = RequestMethod.GET)
    List<JSONObject> findAllToJson();
}
