package com.kerry.client;

import com.kerry.core.SearchParams;
import com.kerry.system.model.DictionaryTypeModel;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 数据字典类型
 * Created by wangshen on 2017/4/21.
 */
@FeignClient(name = "sys-provider")
public interface DictTypeClient {

    @RequestMapping(value = "/dict/type/list", method = RequestMethod.POST)
    PageQuery findByPage(@RequestBody SearchParams params);

    @RequestMapping(value = "/dict/type/save", method = RequestMethod.POST)
    String save(@RequestBody DictionaryTypeModel DictionaryTypeModel);

    @RequestMapping(value = "/dict/type/update", method = RequestMethod.POST)
    String update(@RequestBody DictionaryTypeModel DictionaryTypeModel);

    @RequestMapping(value = "/dict/type/delete/{id}", method = RequestMethod.GET)
    String delete(@PathVariable("id") String id);
}
