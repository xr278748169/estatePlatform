package com.kerry.estate.base.client;

import com.kerry.core.SearchParams;
import com.kerry.estate.base.model.BuildingModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 楼宇管理
 * Created by wangshen on 2017/7/10.
 */
@FeignClient(name = "estate-provider")
public interface BuildingClient {

    @RequestMapping(value = "/building/{code}/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code);

    @RequestMapping(value = "/building/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody BuildingModel buildingModel, @PathVariable("code") String code);

    @RequestMapping(value = "/building/update", method = RequestMethod.POST)
    String update(@RequestBody BuildingModel buildingModel);

    @RequestMapping(value = "/building/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/building/select/{id}", method = RequestMethod.GET)
    BuildingModel selectById(@PathVariable("id") String id);
}
