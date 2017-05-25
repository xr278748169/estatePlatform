package com.kerry.client;

import com.kerry.core.SearchParams;
import com.kerry.system.model.DictionaryModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据字典明细
 * Created by wangshen on 2017/4/21.
 */
@FeignClient(name = "sys-provider")
public interface DictClient {

    @RequestMapping(value = "/dict/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params);

    @RequestMapping(value = "/dict/save", method = RequestMethod.POST)
    String save(@RequestBody DictionaryModel dictionaryModel);

    @RequestMapping(value = "/dict/update", method = RequestMethod.POST)
    String update(@RequestBody DictionaryModel dictionaryModel);

    @RequestMapping(value = "/dict/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);

    @RequestMapping(value = "/dict/json", method = RequestMethod.GET)
    String findDictJson();
}
