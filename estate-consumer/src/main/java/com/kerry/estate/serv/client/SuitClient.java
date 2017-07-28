package com.kerry.estate.serv.client;

import com.kerry.core.SearchParams;
import com.kerry.estate.serv.model.SuitModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 投诉建议
 * Created by wangshen on 2017/7/27.
 */
@FeignClient(name = "estate-provider")
public interface SuitClient {

    @RequestMapping(value = "/suit/{code}/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code);

    @RequestMapping(value = "/suit/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody SuitModel suitModel, @PathVariable("code") String code);

    @RequestMapping(value = "/suit/update", method = RequestMethod.POST)
    String update(@RequestBody SuitModel suitModel);

    @RequestMapping(value = "/suit/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/suit/select/{id}", method = RequestMethod.GET)
    SuitModel selectById(@PathVariable("id") String id);
}
