package com.kerry.client;

import com.kerry.core.SearchParams;
import com.kerry.system.model.ResModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 系统资源管理
 * Created by wangshen on 2017/4/24.
 */
@FeignClient(name = "sys-provider")
public interface ResClient {

    @RequestMapping(value = "/res/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params);

    @RequestMapping(value = "/res/save", method = RequestMethod.POST)
    String save(@RequestBody ResModel resModel);

    @RequestMapping(value = "/res/update", method = RequestMethod.POST)
    String update(@RequestBody ResModel resModel);

    @RequestMapping(value = "/res/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/res/list/sub/{parentId}", method = RequestMethod.GET)
    List<ResModel> findByParentId(@PathVariable("parentId") String parentId);
}
