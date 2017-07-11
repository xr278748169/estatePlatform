package com.kerry.estate.base.client;

import com.kerry.core.SearchParams;
import com.kerry.estate.base.model.PropertyCoModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 物业公司管理
 * Created by wangshen on 2017/7/4.
 */
@FeignClient(name = "estate-provider")
public interface PropertyCoClient {

    @RequestMapping(value = "/property-co/{code}/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code);

    @RequestMapping(value = "/property-co/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody PropertyCoModel propertyCoModel, @PathVariable("code") String code);

    @RequestMapping(value = "/property-co/update", method = RequestMethod.POST)
    String update(@RequestBody PropertyCoModel propertyCoModel);

    @RequestMapping(value = "/property-co/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/property-co/select/{id}", method = RequestMethod.GET)
    PropertyCoModel selectById(@PathVariable("id") String id);

    @RequestMapping(value = "/property-co/all", method = RequestMethod.GET)
    List<PropertyCoModel> findAll();
}
