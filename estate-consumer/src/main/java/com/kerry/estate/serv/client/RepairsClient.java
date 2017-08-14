package com.kerry.estate.serv.client;

import com.kerry.core.SearchParams;
import com.kerry.estate.dto.RepairsDto;
import com.kerry.estate.serv.model.RepairsModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 报修管理
 * Created by wangshen on 2017/7/27.
 */
@FeignClient(name = "estate-provider")
public interface RepairsClient {

    @RequestMapping(value = "/repairs/{code}/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params, @PathVariable("code") String code);

    @RequestMapping(value = "/repairs/{code}/save", method = RequestMethod.POST)
    String insert(@RequestBody RepairsModel repairsModel, @PathVariable("code") String code);

    @RequestMapping(value = "/repairs/update", method = RequestMethod.POST)
    String update(@RequestBody RepairsModel repairsModel);

    @RequestMapping(value = "/repairs/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/repairs/select/{id}", method = RequestMethod.GET)
    RepairsModel selectById(@PathVariable("id") String id);

    @RequestMapping(value = "/repairs/save", method = RequestMethod.POST)
    String saveRepairs(@RequestBody RepairsDto repairsDto);
}
